 $(document).ready(function() {
    const $modal = $('#editModal');

    // 1. 編集リンクをクリックした時の処理
    $('.edit-link').on('click', function(e) {
        e.preventDefault();

        // クリックされた行のデータを取得
        const $row = $(this).closest('tr');
        const code = $row.find('td:eq(0)').text(); // 1番目のtd（コード）
        const name = $row.find('td:eq(1)').text(); // 2番目のtd（銘柄名）
        const market = $row.find('td:eq(2)').text(); // 3番目のtd（市場）

        // モーダルのインプットに値をセット
        $('#modalCode').val(code);
        $('#modalName').val(name);
        $('#modalMarket').val(market);

        // モーダルを表示
        $modal.fadeIn(200);
    });

    // 2. モーダルを閉じる処理
    $('.close-btn, .close-modal').on('click', function() {
        $modal.fadeOut(200);
    });

    // モーダルの外側をクリックしたら閉じる
    $(window).on('click', function(e) {
        if ($(e.target).is($modal)) {
            $modal.fadeOut(200);
        }
    });

    // 3. 更新処理 (Ajax)
    $('#editForm').on('submit', function(e) {
        e.preventDefault();

        const formData = {
            code: $('#modalCode').val(),
            stock_name: $('#modalName').val(),
            market: $('#modalMarket').val()
        };

        console.log("送信データ:", formData);

        // ここにSpring Bootへの$.ajax({ type: 'POST', ... }) 処理を書く
        alert('更新処理をここに実装します: ' + formData.stock_name);
        $modal.fadeOut(200);
    });
});