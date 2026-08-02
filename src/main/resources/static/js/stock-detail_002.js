document.addEventListener("DOMContentLoaded", () => {
  // ===================================
  // シミュレーションパラメータセクション
  // ===================================

  // << WACC(割引率,一年後の100万円)(sliderの設定・動きを単数のみ制御) >>
  const waccSlider = document.getElementById("wacc-slider");
  const waccValueDisplay = document.getElementById("wacc-value");

  // 初期状態の数値（HTMLに書いた初期値）を反映させる
  const waccInitialValue = parseFloat(waccSlider.value);
  waccValueDisplay.textContent = `${waccInitialValue.toFixed(1)}%`;

  // スライダーが動かされた時に実行する処理
  waccSlider.addEventListener("input", (event) => {
    // 現在のスライダーの値を取得し、% をつけて表示を更新
    const waccCurrentValue = parseFloat(event.target.value);
    waccValueDisplay.textContent = `${waccCurrentValue.toFixed(1)}%`;
  });

  // << 将来5年間の成長率, 永久成長率(sliderの設定・動きを複数同時に制御) >>
  // 1. ページ内のすべてのスライダーグループを取得
  const groups = document.querySelectorAll(".slider-param-group");

  // 2. それぞれのグループに対して、個別にイベントを設定する
  groups.forEach((group) => {
    // グループ「内」にあるスライダーと表示用要素をピンポイントで取得
    const slider = group.querySelector(".slider");
    const valueDisplay = group.querySelector(".slider-value");

    // 初期状態の数値（HTMLに書いた初期値）を反映させる
    const initialValue = parseFloat(slider.value);
    valueDisplay.textContent = `${initialValue.toFixed(1)}%`;

    // そのグループのスライダーが動いたときだけ、同じグループのラベルを書き換える
    slider.addEventListener("input", (event) => {
      const currentValue = parseFloat(event.target.value);
      valueDisplay.textContent = `${currentValue.toFixed(1)}%`;
    });
  });

  // ===================================
  // 理論株価レンジ比較セクション
  // ===================================

  // ===================================
  // 各モデル結果セクション
  // ===================================

  // ===================================
  // 主要指標セクション
  // ===================================

  // ===================================
  // 業績グラフセクション
  // ===================================
  $(".tab-btn").on("click", function () {
    const targetTab = $(this).data("tab");

    // ボタンの見た目の切り替え
    $(".tab-btn")
      .removeClass("border-emerald-500 text-emerald-400")
      .addClass("border-transparent text-gray-400 hover:text-white");
    $(this)
      .addClass("border-emerald-500 text-emerald-400")
      .removeClass("border-transparent text-gray-400");

    // コンテンツの切り替え表示
    $(".tab-content").addClass("hidden").removeClass("block");
    $("#" + targetTab)
      .removeClass("hidden")
      .addClass("block");
  });

  // 各カテゴリのグラフ初期化 (収益性)
  new Chart($("#profitChart"), {
    type: "line",
    data: {
      labels: chartData.fiscalYearLabels,
      datasets: [
        {
          label: "ROE",
          data: chartData.roeList,
          borderColor: "#2563eb",
          yAxisID: "y",
        },
        {
          label: "売上高総利益率",
          data: chartData.grossMarginList,
          borderColor: "#60a5fa",
          yAxisID: "y",
        },
        {
          label: "売上高純利益率",
          data: chartData.netMarginList,
          borderColor: "#94a3b8",
          yAxisID: "y",
        },
        {
          label: "EPS",
          data: chartData.epsList,
          backgroundColor: "rgba(4, 75, 48, 0.7)",
          type: "bar",
          yAxisID: "y1",
          barThickness: 30,
          maxBarThickness: 50,
        },
      ],
    },
    options: {
      maintainAspectRatio: false,
      scales: {
        y: {
          position: "right",
          grid: { drawOnChartArea: false },
          title: { display: true, text: "パーセント（％）" },
          beginAtZero: true,
        },
        y1: {
          position: "left",
          grid: { drawOnChartArea: false },
          title: { display: true, text: "EPS (円)" },
          beginAtZero: false,
          // 最小値の下に10%のバッファ（余白）を作る
          min: Math.min(...chartData.epsList.filter((v) => v !== null)) * 0.95,
          // 最大値の上に10%のバッファを作る
          suggestedMax:
            Math.max(...chartData.epsList.filter((v) => v !== null)) * 1.1,
        },
      },
    },
  });

  // まず最大値を計算する（efficiencyChartの外で実行）
  const allTurnoverValues = [
    ...chartData.assetTurnoverList,
    ...chartData.inventoryTurnoverList,
    ...chartData.receivablesTurnoverList,
  ].filter((v) => v !== null && v !== undefined);

  const maxVal =
    allTurnoverValues.length > 0 ? Math.max(...allTurnoverValues) : 10;

  // 計算した maxVal を使ってグラフを生成する
  new Chart($("#efficiencyChart"), {
    type: "line",
    data: {
      labels: chartData.fiscalYearLabels,
      datasets: [
        {
          label: "総資産回転率",
          data: chartData.assetTurnoverList,
          borderColor: "#94a3b8",
          backgroundColor: "#94a3b8",
          yAxisID: "y",
        },
        {
          label: "棚卸資産回転率",
          data: chartData.inventoryTurnoverList,
          borderColor: "#60a5fa",
          backgroundColor: "#60a5fa",
          yAxisID: "y",
        },
        {
          label: "売上債権回転率",
          data: chartData.receivablesTurnoverList,
          borderColor: "#2563eb",
          backgroundColor: "#2563eb",
          yAxisID: "y",
        },
      ],
    },
    options: {
      maintainAspectRatio: false,
      scales: {
        y: {
          beginAtZero: true,
          title: { display: true, text: "回転率 (回)" },
          suggestedMax: maxVal * 1.5, // ここで計算済みの変数を使う
        },
      },
    },
  });

  // 安全性グラフ (Safety Chart)
  // 自己資本比率を折れ線、D/Eレシオを棒グラフで表示する複合チャート
  new Chart($("#safetyChart"), {
    type: "bar",
    data: {
      labels: chartData.fiscalYearLabels,
      datasets: [
        {
          label: "自己資本比率 (%)",
          data: chartData.equityRatioList,
          type: "line",
          borderColor: "#10b981",
          backgroundColor: "#10b981",
          yAxisID: "y",
          tension: 0.1,
        },
        {
          label: "D/Eレシオ",
          data: chartData.debtEquityRatioList,
          backgroundColor: "rgba(148, 163, 184, 1.0)",
          yAxisID: "y1",
          barThickness: 20,
          maxBarThickness: 50,
        },
        {
          label: "インタレスト・カバレッジ・レシオ",
          data: chartData.interestCoverageRatioList,
          backgroundColor: "rgba(148, 163, 184, 0.5)",
          yAxisID: "y1",
          barThickness: 20,
          maxBarThickness: 50,
        },
      ],
    },
    options: {
      maintainAspectRatio: false,
      scales: {
        y: {
          position: "right",
          beginAtZero: true,
          title: { display: true, text: "自己資本比率 (%)" },
          // 右側のグリッド線を消してスッキリさせる
          grid: { drawOnChartArea: true },
          max:
            Math.max(...chartData.equityRatioList.filter((v) => v !== null)) *
            1.5,
        },
        y1: {
          type: "logarithmic", // ここを対数に設定
          position: "left",
          beginAtZero: true,
          title: { display: true, text: "倍率" },
          // 左側のグリッド線を消してスッキリさせる
          grid: { drawOnChartArea: false },
          // 対数軸の場合、0は表示できないので最小値に注意
          min: 0.1,
        },
      },
    },
  });

  // キャッシュフローグラフ (CF Chart)
  // 営業CFとフリーCFの推移を可視化する棒グラフ
  new Chart($("#cfChart"), {
    type: "bar",
    data: {
      labels: chartData.fiscalYearLabels,
      datasets: [
        {
          label: "フリーCF",
          data: chartData.fcfList,
          backgroundColor: "rgba(148, 163, 184, 0.2)",
          barThickness: 20,
          maxBarThickness: 50,
        },
        {
          label: "営業CFマージン",
          data: chartData.operationCfMarginList,
          type: "line",
          backgroundColor: "#020508",
          borderColor: "#020306",
          yAxisID: "y1",
          tension: 0.1,
        },
      ],
    },
    options: {
      maintainAspectRatio: false,
      interaction: {
        mode: "index",
        intersect: false,
      },
      scales: {
        y: {
          position: "left",
          beginAtZero: false,
          title: { display: true, text: "フリーCF (百万円)" },
          min: Math.min(...chartData.fcfList.filter((v) => v !== null)) * 0.95,
        },
        y1: {
          position: "right",
          beginAtZero: true,
          title: { display: true, text: "営業CFマージン (%)" },
          // 右側のグリッドを消してスッキリ
          grid: { drawOnChartArea: false },
          max:
            Math.max(
              ...chartData.operationCfMarginList.filter((v) => v !== null),
            ) * 1.5,
        },
      },
    },
  });

  // ===================================
  // 共通処理セクション
  // ===================================
});
