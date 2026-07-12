import { describe, test, expect, beforeEach } from 'vitest';
import '../../main/resources/static/js/pv_calculator.js';

describe('Spring Boot フロントエンドJSテスト', () => {
    let calculator;

    beforeEach(() => {
        calculator = new global.pv_calculator();
    });

    test('dcf_calc が正常に動作すること', () => {
        // テスト値（1.2億, 5%, 9%, 2%）で実際に計算が通るか検証
        const result = calculator.dcf_calc(120000000, 0.05, 0.09, 0.02);
        expect(result).toBeCloseTo(1987521383, -1);
        });

    test('calculate_fair_stock_price で適正株価が250円になること', () => {
        const business_value = 2500000000; // 25億円
        const financial_data = {
            non_operating_assets: {
                cash_and_equivalents: 500000000,
                investment_securities: 300000000
            },
            interest_bearing_debt: {
                short_term_borrowings: 100000000,
                current_portion_of_long_term_debt: 50000000,
                corporate_bonds: 200000000,
                long_term_borrowings: 400000000,
                lease_obligations: 50000000
            },
            shares_outstanding: 10000000 // 1000万株
        };

        const result = calculator.calculate_fair_stock_price(business_value, financial_data);
        expect(result.fair_stock_price).toBe(250);
    });
});