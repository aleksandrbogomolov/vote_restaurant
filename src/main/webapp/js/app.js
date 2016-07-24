$('.scroll-top').click(function () {
    $('body,html').animate({scrollTop: 0}, 1000);
});

$('.collapse').collapse('toggle');

function makeEditable() {
    $('#addRestaurant').click(function () {
        $('#new-restaurant').modal();
    });

    $('#new-restaurant').submit(function () {
        saveRestaurant($('#details-form'));
        return false;
    });

    $("form[name='update-restaurant']").submit(function () {
        saveRestaurant($(this));
        return false;
    });

    $('.delete-restaurant').click(function () {
        deleteRestaurant($(this).attr('id'));
    });

    $('.delete-dish').click(function () {
        var form = ($(this).parent());
        var array = form.serializeArray();
        deleteDish(array);
    });

    $('.vote').click(function () {
        addVote($(this).attr('id'));
    });

    $('.voteClear').click(function () {
        clearVote();
    });

    //noinspection JSUnresolvedFunction
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
            $('#new-restaurant').modal('hide');
            updateDesk();
            successNoty('Create or update restaurant');
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

function deleteDish(array) {
    $.ajax({
        url: dishUrl + array[1].value + '/' + array[0].value,
        type: 'DELETE',
        success: function () {
            updateDesk();
            successNoty('Delete dish with id ' + array[0]);
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