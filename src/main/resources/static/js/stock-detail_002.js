// ===================================
// WACC(割引率,一年後の100万円)(sliderの設定・動きを単数のみ制御)
// ===================================
const waccSlider = document.getElementById('wacc-slider');
const waccValueDisplay = document.getElementById('wacc-value');

// 初期状態の数値（HTMLに書いた初期値）を反映させる
const waccInitialValue = parseFloat(waccSlider.value);
waccValueDisplay.textContent = `${waccInitialValue.toFixed(1)}%`;

// スライダーが動かされた時に実行する処理
waccSlider.addEventListener('input', (event) => {
    // 現在のスライダーの値を取得し、% をつけて表示を更新
    const waccCurrentValue = parseFloat(event.target.value);
    waccValueDisplay.textContent = `${waccCurrentValue.toFixed(1)}%`;
});

// ===================================
// 将来5年間の成長率, 永久成長率(sliderの設定・動きを複数同時に制御)
// ===================================
// 1. ページ内のすべてのスライダーグループを取得
const groups = document.querySelectorAll('.slider-param-group');

// 2. それぞれのグループに対して、個別にイベントを設定する
groups.forEach((group) => {
    // グループ「内」にあるスライダーと表示用要素をピンポイントで取得
    const slider = group.querySelector('.slider');
    const valueDisplay = group.querySelector('.slider-value');

    // 初期状態の数値（HTMLに書いた初期値）を反映させる
    const initialValue = parseFloat(slider.value);
    valueDisplay.textContent = `${initialValue.toFixed(1)}%`;

    // そのグループのスライダーが動いたときだけ、同じグループのラベルを書き換える
    slider.addEventListener('input', (event) => {
        const currentValue = parseFloat(event.target.value);
        valueDisplay.textContent = `${currentValue.toFixed(1)}%`;
    });
});