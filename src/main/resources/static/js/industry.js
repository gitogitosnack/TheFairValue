$(document).ready(function () {

    const $modal = $('#editModal');

    $('#addIndustryBtn').on('click', function () {
        $('#editForm')[0].reset();
        $('#modalId').val('');
        $('.modal-header h2').text('業種の登録');
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
        const targetUrl = (id === '' || id === null) ? 'rest_industries/insert' : 'rest_industries/update';

        const formData = {
            id: id,
            name: $('#modalName').val(),
            sectorName: $('#modalSectorName').val(),
            description: $('#modalDescription').val(),
            avgPer: $('#modalAvgPer').val()
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
        const name = $row.find('td:eq(1)').text().trim();
        const sector = $row.find('td:eq(2)').text().trim();

        if (!confirm(`業種「${name} (${sector})」を削除してもよろしいですか？`)) {
            return false;
        }

        $.ajax({
            url: '/rest_industries/delete/' + id,
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
        const name = $row.find('td:eq(1)').text().trim();
        const sectorName = $row.find('td:eq(2)').text().trim();
        const avgPer = $row.find('td:eq(3)').text().trim();
        const description = $row.find('td:eq(4)').text().trim();

        $('#modalId').val(id);
        $('#modalName').val(name);
        $('#modalSectorName').val(sectorName);
        $('#modalAvgPer').val(avgPer);
        $('#modalDescription').val(description);

        $('.modal-header h2').text('業種情報の編集');
        $modal.fadeIn(200);
    });

});
