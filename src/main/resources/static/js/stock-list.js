
 $(document).ready(function() {
    const $modal = $('#editModal');

    // 1. 編集リンクをクリックした時の処理
    $('.edit-link').on('click', function(event) {
        event.preventDefault();

        // クリックされた行のデータを取得
        const $row = $(this).closest('tr');
        const id = $(this).data('id');
        const code = $row.find('td:eq(0)').text().trim(); // 1番目のtd（コード）
        const name = $row.find('td:eq(1)').text(); // 2番目のtd（銘柄名）
        const market_name = $row.find('td:eq(2)').text(); // 3番目のtd（市場）

        // モーダルのインプットに値をセット
        $('#modalId').val(id);
        $('#modalCode').val(code);
        $('#modalName').val(name);
        $('#modalMarket_name').val(market_name);

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

        const id = $('#modalId').val();
        // idに値が入っているかどうかでコントローラメソッド先をカエル
        // 空なら、新規登録
        // 値があれば、更新処理
        const targetUrl = (id === "" || id === null) ? 'rest_stock_list/insert' : 'rest_stock_list/update';

        const formData = {
            id: id
            ,code: $('#modalCode').val()
            ,name: $('#modalName').val()
            ,market_name: $('#modalMarket_name').val()
        };

        console.log("送信データ:", formData);

        // ここにSpring Bootへの$.ajax({ type: 'POST', ... }) 処理を書く
        $.ajax({
            url: targetUrl    // サーバ側のコントローラに書かれているURL
            ,type: 'POST'                     // HTTPメソッド
            ,contentType: 'application/json'   // 送るデータ形式
            ,data: JSON.stringify(formData)    // JSオブジェクトをJSON文字列に変換（変換しないと415エラーになるらしい）
            //,dataType: 'json'                   // サーバーから返ってくるデータの形式
        })
        .done(function(response) {
            // process after success
            alert("the record updated.");
            location.reload();
        })
        .fail(function(xhr, status, error) {
            // process after failed
            alert("Error occurred.");
            console.log(error);
        });
        // alert('更新処理をここに実装します: ' + formData.stock_name);
        $modal.fadeOut(200);
    });


    // 削除リンクがクリックされた時の処理
    $('.delete-link').on('click', function(event) {
        event.preventDefault(); // リンクのデフォルト遷移を無効化

        // 1. 削除対象の行とデータを特定
        const $row = $(this).closest('tr');
        const id = $(this).data('id');
        const code = $row.find('td:eq(0)').text(); // 1番目のtdからコード取得
        const name = $row.find('td:eq(1)').text(); // 2番目のtdから銘柄名取得

        // 2. 削除確認
        if (!confirm(`銘柄「${name} (${code})」を削除してもよろしいですか？`)) {
            return false;
        }

        // 3. 非同期通信(Ajax)で削除リクエストを送信
        // ※URLはバックエンドのControllerに合わせて調整してください
        $.ajax({
            url: '/rest_stock_list/delete/' + id,
            type: 'DELETE', // Spring Boot側で@DeleteMappingを使用する場合
            beforeSend: function() {
                // 二重送信防止などが必要な場合は,ここで処理
            }
        })
        .done(function(response) {
            // 4. 成功時の処理：テーブルから行を削除
            $row.fadeOut(400, function() {
                $(this).remove();
                alert('削除が完了しました。');
            });
        })
        .fail(function(xhr) {
            // 5. 失敗時の処理
            console.error('Error:', xhr);
            alert('削除に失敗しました。時間をおいて再度お試しください。');
        });
    });

    // 新規登録ボタンが押されたとき
    $('#addStockBtn').on('click', function() {
        // 1. フォームをリセット（前回の入力内容を消す）
        $('#editForm')[0].reset();

        // 2. 隠し値のIDを空にする（ここが重要！）
        $('#modalId').val('');

        // 3. モーダルのタイトルを「新規登録」に変更（任意）
        $('.modal-header h2').text('新規銘柄の登録');

        // 4. モーダルを表示
        $('#editModal').fadeIn(200);
    });

});