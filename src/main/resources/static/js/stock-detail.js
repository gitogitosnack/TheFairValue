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
            labels: ['2021', '2022', '2023', '2024', '2025'],
            datasets: [
                { label: 'ROE (%)', data: [8.5, 9.2, 10.1, 11.5, 12.2], borderColor: '#2563eb', yAxisID: 'y' },
                { label: 'EPS (円)', data: [420, 450, 485, 540, 585], backgroundColor: 'rgba(16, 185, 129, 0.2)', type: 'bar', yAxisID: 'y1' }
            ]
        },
        options: { maintainAspectRatio: false, scales: { y1: { position: 'right', grid: { drawOnChartArea: false } } } }
    });

    // 効率性グラフ
    new Chart($('#efficiencyChart'), {
        type: 'bar',
        data: {
            labels: ['2021', '2022', '2023', '2024', '2025'],
            datasets: [{ label: '総資産回転率', data: [1.1, 1.15, 1.2, 1.2, 1.3], backgroundColor: '#94a3b8' }]
        },
        options: { maintainAspectRatio: false }
    });

    // 4. 安全性グラフ (Safety Chart)
    // 自己資本比率を折れ線、D/Eレシオを棒グラフで表示する複合チャートの例
    new Chart($('#safetyChart'), {
        type: 'line',
        data: {
            labels: ['2021', '2022', '2023', '2024', '2025'],
            datasets: [
                {
                    label: '自己資本比率 (%)',
                    data: [55.2, 58.0, 60.5, 62.5, 63.8],
                    borderColor: '#10b981', // success color
                    backgroundColor: '#10b981',
                    yAxisID: 'y',
                    tension: 0.1
                },
                {
                    label: 'D/Eレシオ',
                    data: [0.45, 0.42, 0.38, 0.35, 0.33],
                    type: 'bar',
                    backgroundColor: 'rgba(148, 163, 184, 0.5)', // slate-400
                    yAxisID: 'y1'
                }
            ]
        },
        options: {
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: false,
                    title: { display: true, text: '自己資本比率 (%)' }
                },
                y1: {
                    position: 'right',
                    beginAtZero: true,
                    title: { display: true, text: 'D/Eレシオ' },
                    grid: { drawOnChartArea: false } // 右側のグリッド線を消してスッキリさせる
                }
            }
        }
    });

    // 5. キャッシュフローグラフ (CF Chart)
    // 営業CFとフリーCFの推移を比較する棒グラフ
    new Chart($('#cfChart'), {
        type: 'bar',
        data: {
            labels: ['2021', '2022', '2023', '2024', '2025'],
            datasets: [
                {
                    label: '営業CF',
                    data: [1200, 1500, 1450, 1800, 2100],
                    backgroundColor: '#2563eb', // primary color
                },
                {
                    label: 'フリーCF',
                    data: [400, 700, 650, 900, 1200],
                    backgroundColor: '#60a5fa', // lighter blue
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
                    beginAtZero: true,
                    title: { display: true, text: '金額 (百万円)' }
                }
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
