// テスト実行時に logger 未定義エラーが出ないよう、簡易的なダミーを定義
const logger = typeof window !== 'undefined' && window.logger ? window.logger : {
    info: (msg) => console.log(`[INFO] ${msg}`),
    error: (msg) => console.error(`[ERROR] ${msg}`)
};

class pv_calculator {

    // DCF法を用いて適正現在株価を予測するロジック
    dcf_calc(fcf, growth_ratio, discount_ratio, perpetual_growth_ratio, financial_data){
        logger.info("dcf_calc method started.");

        // 0. エラーハンドリング: 割引率が永続成長率以下の場合、ゴードン・モデルは成立しません
        if (discount_ratio <= perpetual_growth_ratio) {
            logger.error("Discount ratio must be greater than perpetual growth ratio.");
            throw new Error("割引率は永続成長率よりも大きくする必要があります。");
        }

        // 1. 1〜5年目の予測FCFとその事業価値の計算
        const term = 5;
        let discounted_years = [];
        let business_value = 0;
        let current_fcf;

        for(let i = 1; i <= term; i++) {
            current_fcf = fcf * Math.pow(1 + growth_ratio, i);
            let discounted_year = current_fcf / Math.pow(1 + discount_ratio, i);
            discounted_years.push(discounted_year);
        }

        for (const year of discounted_years){
            business_value += year;
        }

        // 2. ターミナルバリュー（6年目以降の永続価値）の計算
        // 6年目のFCFを予測
        let year_6_fcf = current_fcf * (1 + perpetual_growth_ratio);
        // 5年時点でのターミナルバリューを算出（ゴードン・グロース・モデル）
        let terminal_value = year_6_fcf / (discount_ratio - perpetual_growth_ratio);
        // ターミナルバリューを事業価値に割引
        let discounted_terminal_value = terminal_value / Math.pow(1 + discount_ratio, term);

        // 3. 1〜5年目の事業価値とターミナルバリューの事業価値を合算
        business_value += discounted_terminal_value;

        // 非事業資産の集計
        const non_operating_assets = Object.values(financial_data.non_operating_assets)
            .reduce((sum, value) => sum + value, 0);
        
        // 有利子負債の集計
        const interest_bearing_debt = Object.values(financial_data.interest_bearing_debt)
            .reduce((sum, value) => sum + value, 0);

        // 株主価値の算出
        const equity_value = business_value + non_operating_assets - interest_bearing_debt;

        // 1株あたりの適正株価の算出
        const shares = financial_data.shares_outstanding;
        if (!shares || shares <= 0) {
            throw new Error("発行済株式総数が不正です。");
        }

        // 4. 現在の適正株価算出
        const fair_price = equity_value / shares;

        logger.info("dcf_calc method ended.");
        return fair_price;
    }
}

// ブラウザ環境（window）を壊さず、Node.js環境（Vitest）の時だけグローバルに展開する
if (typeof global !== 'undefined') {
    global.pv_calculator = pv_calculator;
}