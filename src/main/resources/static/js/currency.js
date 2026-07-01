$(document).ready(function () {

    const $modal = $('#editModal');

    $('#addCurrencyBtn').on('click', function () {
        $('#editForm')[0].reset();
        $('#modalId').val('');
        $('.modal-header h2').text('通貨の登録');
        $modal.fadeIn(200);
    });

    $(window).on('click', function (event) {
        if ($(event.target).is($modal)) {
            $modal.fadeOut(200);
        }
    });

    $('.close-btn, .close-modal').on('click', function () {
        $modal.fadeOut(200);
    });

    $('#editForm').on('submit', function (event) {
        event.preventDefault();

        const id = $('#modalId').val();
        const targetUrl = (id === '' || id === null) ? 'rest_currencies/insert' : 'rest_currencies/update';

        const formData = {
            id: id,
            code: $('#modalCode').val(),
            symbol: $('#modalSymbol').val()
        };

        $.ajax({
            url: targetUrl,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData)
        })
            .done(function () {
                alert('保存しました。');
                location.reload();
            })
            .fail(function (xhr) {
                alert('保存に失敗しました。');
                console.error(xhr);
            });

        $modal.fadeOut(200);
    });

    $('.delete-link').on('click', function (event) {
        event.preventDefault();

        const $row = $(this).closest('tr');
        const id = $(this).data('id');
        const code = $row.find('td:eq(1)').text().trim();
        const symbol = $row.find('td:eq(2)').text().trim();

        if (!confirm(`通貨「${code} (${symbol})」を削除してもよろしいですか？`)) {
            return false;
        }

        $.ajax({
            url: '/rest_currencies/delete/' + id,
            type: 'DELETE'
        })
            .done(function () {
                $row.fadeOut(400, function () {
                    $(this).remove();
                    alert('削除が完了しました。');
                });
            })
            .fail(function (xhr) {
                alert('削除に失敗しました。');
                console.error(xhr);
            });
    });

    $('.edit-link').on('click', function (event) {
        event.preventDefault();

        const $row = $(this).closest('tr');
        const id = $row.find('td:eq(0)').text().trim();
        const code = $row.find('td:eq(1)').text().trim();
        const symbol = $row.find('td:eq(2)').text().trim();

        $('#modalId').val(id);
        $('#modalCode').val(code);
        $('#modalSymbol').val(symbol);

        $('.modal-header h2').text('通貨情報の編集');
        $modal.fadeIn(200);
    });

});
