const waccSlider = document.getElementById('wacc-slider');
const waccValueDisplay = document.getElementById('wacc-value');

// スライダーが動かされた時に実行する処理
waccSlider.addEventListener('input', (event) => {
    // 現在のスライダーの値を取得し、% をつけて表示を更新
    waccValueDisplay.textContent = `${event.target.value}%`;
});

const future5YrsSlider = document.getElementById('future-5years-growth-ratio-slider');
const future5YrsValueDisplay = document.getElementById('future-5years-growth-ratio-value');

// スライダーが動かされた時に実行する処理
future5YrsSlider.addEventListener('input', (event) => {
    // 現在のスライダーの値を取得し、% をつけて表示を更新
    future5YrsValueDisplay.textContent = `${event.target.value}%`;
});

const perpetualSlider = document.getElementById('perpetual-growth-ratio-slider');
const perpetualValueDisplay = document.getElementById('perpetual-growth-ratio-value');

// スライダーが動かされた時に実行する処理
perpetualSlider.addEventListener('input', (event) => {
    // 現在のスライダーの値を取得し、% をつけて表示を更新
    perpetualValueDisplay.textContent = `${event.target.value}%`;
});