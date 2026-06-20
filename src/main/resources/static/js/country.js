$(document).ready(function () {

    const $modal = $('#editModal');

    // 新規登録ボタンが押されたとき
    $('#addCountryBtn').on('click', function () {
        $('#editForm')[0].reset();
        $('#modalId').val('');
        $('.modal-header h2').text('国の登録');
        $('#editModal').fadeIn(200);
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

        const targetUrl = (id === "" || id === null) ? 'rest_countries/insert' : 'rest_countries/update';

        const formData = {
            id: id
            , code: $('#modalCode').val()
            , name: $('#modalName').val()
        };

        console.log("送信データ:", formData);

        $.ajax({
            url: targetUrl
            , type: 'POST'
            , contentType: 'application/json'
            , data: JSON.stringify(formData)
        })
            .done(function (response) {
                alert("the record updated.");
                location.reload();
            })
            .fail(function (xhr, status, error) {
                alert("Error occurred.");
                console.log(error);
            });

        $modal.fadeOut(200);
    });

});