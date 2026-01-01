 $(document).ready(function() {
    const $modal = $('#editModal');

    // 1. 編集リンクをクリックした時の処理
    $('.edit-link').on('click', function(event) {
        event.preventDefault();

        // クリックされた行のデータを取得
        const $row = $(this).closest('tr');
        const code = $row.find('td:eq(0)').text(); // 1番目のtd（コード）
        const name = $row.find('td:eq(1)').text(); // 2番目のtd（銘柄名）
        const market = $row.find('td:eq(2)').text(); // 3番目のtd（市場）

        // モーダルのインプットに値をセット
        $('#modalCode').val(code);
        $('#modalStockName').val(name);
        $('#modalMarket').val(market);

        // モーダルを表示
        $modal.fadeIn(200);
    });

    // 2. モーダルを閉じる処理
    $('.close-btn, .close-modal').on('click', function() {
        $modal.fadeOut(200);
    });

    // モーダルの外側をクリックしたら閉じる
    $(window).on('click', function(event) {
        if ($(event.target).is($modal)) {
            $modal.fadeOut(200);
        }
    });

    // 3. 更新処理 (Ajax)
    $('#editForm').on('submit', function(event) {
        event.preventDefault();

        const formData = {
            code: $('#modalCode').val(),
            stockName: $('#modalStockName').val(),
            market: $('#modalMarket').val()
        };

        console.log("送信データ:", formData);

        // ここにSpring Bootへの$.ajax({ type: 'POST', ... }) 処理を書く
        $.ajax({
            url: '/rest_stock_list/update',    // サーバ側のコントローラに書かれているURL
            type: 'POST',                      // HTTPメソッド
            contentType: 'application/json',   // 送るデータ形式
            data: JSON.stringify(formData),    // JSオブジェクトをJSON文字列に変換（変換しないと415エラーになるらしい）
            dataType: 'json'                   // サーバーから返ってくるデータの形式
        })
        .done(function(response) {
            // process after success
            alert("the record updated. Code:" + respose.xxx);
        })
        .fail(function(xhr, status, error) {
            // process after failed
            alert("Error occurred.");
            console.log(error);
        });
        // alert('更新処理をここに実装します: ' + formData.stock_name);
        $modal.fadeOut(200);
    });
});