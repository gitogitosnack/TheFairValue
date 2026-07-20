import { describe, test, expect, beforeEach } from "vitest";
import "../../main/resources/static/js/pv_calculator.js";

describe("Spring Boot フロントエンドJSテスト", () => {
  let calculator;
  let validFinancialData;

  beforeEach(() => {
    calculator = new global.pv_calculator();

    // 各テスト毎のモックデータをセット
    validFinancialData = {
      non_operating_assets: {
        cash_and_equivalents: 500000000, // 5億円
        investment_securities: 300000000, // 3億円
      },
      interest_bearing_debt: {
        short_term_borrowings: 100000000, // 1億円
        current_portion_of_long_term_debt: 50000000, // 0.5億円
        corporate_bonds: 200000000, // 2億円
        long_term_borrowings: 400000000, // 4億円
        lease_obligations: 50000000, // 0.5億円
      },
      shares_outstanding: 10000000, // 1,000万株
    };
  });

  test("正常系: 正しいパラメータと決算データで適正株価 with DCF法 が正しく算出されること", () => {
    // 条件: FCF 1.2億, 成長率 5%, 割引率 9%, 永続成長率 2%
    const fairPrice = calculator.dcf_calc(
      120000000,
      0.05,
      0.09,
      0.02,
      validFinancialData,
    );
    // 事業価値(約19.87億円) ÷ 1000万株 = 約198.75円 になることを検証
    // 小数点第2位まで一致しているかチェック (198.75円)
    expect(fairPrice).toBeCloseTo(198.75, 2);
  });

  test("異常系: 割引率 <= 永続成長率 のときにエラーを投げること", () => {
    // 割引率(0.02) <= 永続成長率(0.02) でゴードンモデルが崩壊するケース
    expect(() => {
      calculator.dcf_calc(120000000, 0.05, 0.02, 0.02, validFinancialData);
    }).toThrow("割引率は永続成長率よりも大きくする必要があります。");
  });

  test("異常系: 発行済株式総数が 0 のときにエラーを投げること", () => {
    // 株式総数を 0 に上書き（ゼロ除算の防止チェック）
    validFinancialData.shares_outstanding = 0;

    expect(() => {
      calculator.dcf_calc(120000000, 0.05, 0.09, 0.02, validFinancialData);
    }).toThrow("発行済株式総数が不正です。");
  });
});
