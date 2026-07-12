import { describe, test, expect, beforeEach } from 'vitest';
// Spring Bootの静的ファイルからクラスをインポート
const pv_calculator = import('../../main/resources/static/js/pv_calculator.js');

describe('Spring Boot フロントエンドJSテスト', () => {
    let calculator;

    beforeEach(() => {
        calculator = new pv_calculator();
    });

    test('dcf_calc が正常に動作すること', () => {
        const result = calculator.dcf_calc(120, 0.05, 0.09, 0.02);
        expect(result).toBeDefined();
    });
});