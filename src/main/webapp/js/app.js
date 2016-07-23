$('.scroll-top').click(function () {
    $('body,html').animate({scrollTop: 0}, 1000);
});

$('.collapse').collapse('toggle');

function makeEditable() {
    $('#addRestaurant').click(function () {
        $('#newRestaurant').modal();
    });

    $('#newRestaurant').submit(function () {
        form = $('#detailsForm');
        saveRestaurant(form);
        return false;
    });

    $('#updateRestaurant').submit(function () {
        form = $(this);
        saveRestaurant(form);
        return false;
    });

    $('.delete').click(function () {
        deleteRestaurant($(this).attr('id'));
    });

    $('.vote').click(function () {
        addVote($(this).attr('id'));
    });

    $('.voteClear').click(function () {
        clearVote();
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function updateDesk() {
    $.get(restaurantUrl, function (data) {
        $('#datatable').hide().html(data).fadeIn('fast');
    });

}

function addVote(id) {
    $.ajax({
        url: voteUrl + id,
        type: 'POST',
        success: function () {
            updateDesk();
            successNoty('Add vote');
        }
    });
}

function clearVote() {
    $.ajax({
        url: voteUrl,
        type: 'DELETE',
        success: function () {
            updateDesk();
            successNoty('Clear votes')
        }
    });
}

function saveRestaurant(form) {
    $.ajax({
        url: restaurantUrl,
        type: 'POST',
        data: form.serialize(),
        success: function () {
            $('#newRestaurant').modal('hide');
            updateDesk();
            successNoty('Create new restaurant');
        }
    });
}

function deleteRestaurant(id) {
    $.ajax({
        url: restaurantUrl + id,
        type:'DELETE',
        success: function () {
            updateDesk();
            successNoty('Delete restaurant with id: ' + id);
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: 500
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}