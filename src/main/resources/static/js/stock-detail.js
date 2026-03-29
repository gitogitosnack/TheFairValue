$(function() {
    // 1. レーダーチャート (サマリー用)
    new Chart($('#radarChart'), {
        type: 'radar',
        data: {
            labels: ['収益', '成長', '安全', '割安', '配当'],
            datasets: [{
                data: [4.5, 4, 2, 3.5, 1],
                backgroundColor: 'rgba(37, 99, 235, 0.2)', borderColor: 'var(--primary)', borderWidth: 2
            }]
        },
        options: { scales: { r: { min: 0, max: 5, ticks: { display: false } } },
        plugins: { legend: { display: false } } }
    });

    // 2. タブ切替イベント
    $('.tab-btn').on('click', function() {
        const target = $(this).data('tab');
        $('.tab-btn').removeClass('active');
        $(this).addClass('active');
        $('.tab-content').removeClass('active');
        $('#' + target).addClass('active');
    });

    // 3. 各カテゴリのグラフ初期化 (収益性)
    new Chart($('#profitChart'), {
        type: 'line',
        data: {
            labels: chartData.fiscalYearLabels,
            datasets: [
                { label: 'ROE', data: chartData.roeList, borderColor: '#2563eb', yAxisID: 'y' },
                { label: '売上高総利益率', data: chartData.grossMarginList, borderColor: '#60a5fa', yAxisID: 'y' },
                { label: '売上高純利益率', data: chartData.netMarginList, borderColor: '#94a3b8', yAxisID: 'y' },
                { label: 'EPS', 
                    data: chartData.epsList, 
                    backgroundColor: 'rgba(4, 75, 48, 0.7)', 
                    type: 'bar', 
                    yAxisID: 'y1',
                    barThickness: 30, 
                    maxBarThickness: 50, 
                },
            ]
        },
        options: { maintainAspectRatio: false, 
            scales: {
                y: { 
					position: 'right', 
					grid: { drawOnChartArea: false },
					title: { display: true, text: 'パーセント（％）' }, 
					beginAtZero: true,
				 },
                y1: { 
                    position: 'left',
                    grid: { drawOnChartArea: false }, 
                    title: { display: true, text: 'EPS (円)' }, 
					beginAtZero: false, 
				    // 最小値の下に10%のバッファ（余白）を作る
				    min: Math.min(...chartData.epsList.filter(v => v !== null)) * 0.95,				    
				    // 最大値の上に10%のバッファを作る
				    suggestedMax: Math.max(...chartData.epsList.filter(v => v !== null)) * 1.1,
                },
            }
        }
    });

	// まず最大値を計算する（efficiencyChartの外で実行）
	const allTurnoverValues = [
	    ...chartData.assetTurnoverList,
	    ...chartData.inventoryTurnoverList,
	    ...chartData.receivablesTurnoverList
	].filter(v => v !== null && v !== undefined);

	const maxVal = allTurnoverValues.length > 0 ? Math.max(...allTurnoverValues) : 10;

	// 計算した maxVal を使ってグラフを生成する
	new Chart($('#efficiencyChart'), {
	    type: 'line',
	    data: {
	        labels: chartData.fiscalYearLabels,
	        datasets: [
	            { label: '総資産回転率', data: chartData.assetTurnoverList, borderColor: '#94a3b8', backgroundColor: '#94a3b8', yAxisID: 'y'},
	            { label: '棚卸資産回転率', data: chartData.inventoryTurnoverList, borderColor: '#60a5fa', backgroundColor: '#60a5fa', yAxisID: 'y' },
	            { label: '売上債権回転率', data: chartData.receivablesTurnoverList, borderColor: '#2563eb', backgroundColor: '#2563eb', yAxisID: 'y' },
	        ]
	    },
	    options: { 
	        maintainAspectRatio: false,
	        scales: {
	            y: {
	                beginAtZero: true,
	                title: { display: true, text: '回転率 (回)' },
	                suggestedMax: maxVal * 1.5, // ここで計算済みの変数を使う
	            }
	        }
	    }
	});

    // 4. 安全性グラフ (Safety Chart)
    // 自己資本比率を折れ線、D/Eレシオを棒グラフで表示する複合チャート
    new Chart($('#safetyChart'), {
        type: 'line',
        data: {
            labels: chartData.fiscalYearLabels,
            datasets: [
                {
                    label: '自己資本比率 (%)',
                    data: chartData.equityRatioList,
                    borderColor: '#10b981',
                    backgroundColor: '#10b981',
                    yAxisID: 'y',
                    tension: 0.1
                },
                {
                    label: 'D/Eレシオ',
                    data: chartData.debtEquityRatioList,
                    type: 'bar',
                    backgroundColor: 'rgba(148, 163, 184, 1.0)',
                    // yAxisID: 'y1',
                    barThickness: 20, 
                    maxBarThickness: 50, 
                },
				{
                    label: 'インタレスト・カバレッジ・レシオ',
                    data: chartData.interestCoverageRatioList,
                    type: 'bar',
                    backgroundColor: 'rgba(148, 163, 184, 0.5)',
                    yAxisID: 'y1',
                    barThickness: 20, 
                    maxBarThickness: 50, 
                },
            ]
        },
        options: {
            maintainAspectRatio: false,
            scales: {
                y: {
					position: 'right',
                    beginAtZero: false,
                    title: { display: true, text: '自己資本比率 (%)' },
                    // 右側のグリッド線を消してスッキリさせる
                    grid: { drawOnChartArea: true },
                    max: Math.max(...chartData.equityRatioList.filter(v => v !== null)) * 1.5,
                },
                y1: {
                    position: 'left',
                    beginAtZero: true,
                    title: { display: true, text: '倍率' },
					// 左側のグリッド線を消してスッキリさせる
                    grid: { drawOnChartArea: false },
                    max: Math.max(...chartData.interestCoverageRatioList.filter(v => v !== null)) * 1.1,
                }
            }
        }
    });

    // 5. キャッシュフローグラフ (CF Chart)
    // 営業CFとフリーCFの推移を可視化する棒グラフ
    new Chart($('#cfChart'), {
        type: 'bar',
        data: {
            labels: chartData.fiscalYearLabels,
            datasets: [
                {
                    label: 'フリーCF',
                    data: chartData.fcfList,
                    backgroundColor: 'rgba(148, 163, 184, 0.2)',
                    barThickness: 20, 
                    maxBarThickness: 50,
                },
                {
                    label: '営業CFマージン',
                    data: chartData.operationCfMarginList,
                    type: 'line',
                    backgroundColor: '#020508',
                    borderColor: '#020306',
                    yAxisID: 'y1',
                    tension: 0.1
                }
            ]
        },
        options: {
            maintainAspectRatio: false,
            interaction: {
                mode: 'index',
                intersect: false
            },
            scales: {
                y: {
					position: 'left',
                    beginAtZero: false,
                    title: { display: true, text: 'フリーCF (百万円)' },
					min: Math.min(...chartData.fcfList.filter(v => v !== null)) * 0.95,	
                },
                y1: {
                    position: 'right',
                    beginAtZero: true, 
                    title: { display: true, text: '営業CFマージン (%)' },
					// 右側のグリッドを消してスッキリ
					grid: { drawOnChartArea: false },
					max: Math.max(...chartData.operationCfMarginList.filter(v => v !== null)) * 1.5,
                },
            }
        }
    });
	
	// --- 既存のチャート初期化コードなどはそのまま ---
	const $compareModal = $('#compareModal');
	
	// モーダルの初期状態を非表示に設定（CSSクラスで管理）
	$compareModal.removeClass('show');

    // 比較モーダルを開く
    $('#openCompareModal').on('click', function() {
        $compareModal.addClass('show').fadeIn(200);
    });

    // 閉じるボタン（×ボタン、キャンセルボタン）
    $('#closeCompareModal, #cancelCompareModal').on('click', function() {
        $compareModal.fadeOut(200, function() {
            $compareModal.removeClass('show');
        });
    });

    // モーダルの外側をクリックしたら閉じる
    $(window).on('click', function(event) {
        if ($(event.target).is($compareModal)) {
            $compareModal.fadeOut(200, function() {
                $compareModal.removeClass('show');
            });
        }
    });
});
