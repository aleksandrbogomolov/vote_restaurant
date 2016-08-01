var dishUrl = 'dish/';
var voteUrl = 'vote/';
var profileUrl = 'profile/';
var restaurantUrl = 'restaurant/';

function makeEditable() {

    $('.scroll-top').click(function () {
        $('body,html').animate({scrollTop: 0}, 1000);
    });

    $('.collapse').collapse('toggle');

    $('#addRestaurant').click(function () {
        $('#new-restaurant').modal();
    });

    $('#new-restaurant').submit(function () {
        saveRestaurant($('#details-restaurant'));
        return false;
    });

    $("form[name='update-restaurant']").submit(function () {
        saveRestaurant($(this));
        return false;
    });

    $('.delete-restaurant').click(function () {
        deleteRestaurant($(this).attr('id'));
    });

    $('.add-dish').click(function () {
        var parent = $(this).parent();
        var array = parent.serializeArray();
        document.getElementById('restaurant-id').value = array[0].value;
        $('#new-dish').modal();
    });

    $('#new-dish').submit(function () {
        createDish($('#details-form'));
        return false;
    });

    $('#update-dish').submit(function () {
        createDish($('#update-dish'));
        return false;
    });

    $('#delete-dish').click(function () {
        var form = $(this).parent();
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

function getAllFromUserPage() {
    var table = $('#datatable');
    $.get(restaurantUrl, function (data) {
        $.each(data, function (key, value) {
            table.append('<div class="col-md-4"><div class="thumbnail"><div class="caption">' +
                '<h3 class="caption-label name">' + value.name + '</h3>' +
                '<h4 class="caption-label">' + value.address + '</h4>' +
                '<div class="list-inline">' +
                dishesForUserPage(value.dishes) +
                '</div>' +
                '<h3>' +
                '<a class="vote" id="' + value.id + '"><i class="glyphicon glyphicon-thumbs-up"></i></a>' +
                '<span class="pull-right">' + value.votes.length + '</span>' +
                '</h3></div></div></div>');
        });
        makeEditable();
    }, 'json');
}

function getAllFromAdminPage() {
    var table = $('#datatable');
    $.get(restaurantUrl, function (data) {
        $.each(data, function (key, value) {
            table.append('<div class="col-lg-6"><div class="thumbnail"><div class="caption">' +
                '<form method="post" name="update-restaurant">' +
                '<input type="hidden" value="' + value.id + '" name="id">' +
                '<h3 class="caption-label">' +
                '<input class="form-control input-lg" type="text" value="' + value.name + '" name="name"/></h3>' +
                '<h4 class="caption-label">' +
                '<input class="form-control" type="text" value="' + value.address + '" name="address"></h4>' +
                '<button type="submit" class="btn btn-default">' + table_update + '</button>' +
                '<a class="btn btn-default delete-restaurant" id="' + value.id + '">' + table_delete + '</a>' +
                '<a class="btn btn-default add-dish">' + table_add_dish + '</a>' +
                '</form><br>' +
                '<div>' +
                dishesForAdminPage(value.dishes) +
                '</div><h3>' +
                '<a id="thumb-up"><i class="glyphicon glyphicon-thumbs-up"></i></a>' +
                '<span class="pull-right" id="count">' + value.votes.length + '</span>' +
                '</h3></div></div></div>');
        });
        makeEditable();
    }, 'json');
}

function dishesForUserPage(data) {
    var result = '';
    $.each(data, function (key, value) {
        result += '<span>' + value.name + '</span><span class="pull-right">' + value.price + '</span><br>';
    });
    return result;
}

function dishesForAdminPage(data) {
    var result = '';
    $.each(data, function (key, value) {
        result += '<form id="update-dish" class="form-inline" method="post">' +
            '<input type="hidden" value="' + value.id + '" name="id"/>' +
            '<input type="hidden" value="' + value.restaurant + '" name="restaurant"/>' +
            '<input type="text" class="form-control" value="' + value.name + '" name="name"/>' +
            '<input type="text" class="form-control" value="' + value.price + '" name="price"/>' +
            '<input type="text" class="form-control" value="' + value.typeDish + '" name="typeDish"/>' +
            '<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-ok"></i></button>' +
            '<a class="btn btn-default" id="delete-dish"><i class="glyphicon glyphicon-remove"></i></a></form>';
    });
    return result;
}

function updateDesk() {
    $('#datatable').empty();
    if (window.location.pathname == '/admin') {
        getAllFromAdminPage();
    } else {
        getAllFromUserPage();
    }
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
        type: 'DELETE',
        success: function () {
            updateDesk();
            successNoty('Delete restaurant with id: ' + id);
        }
    });
}

function createDish(form) {
    $.ajax({
        url: dishUrl,
        type: 'POST',
        data: form.serialize(),
        success: function () {
            $('#new-dish').modal('hide');
            updateDesk();
            successNoty('Create new dish')
        }
    });
}

function deleteDish(array) {
    $.ajax({
        url: dishUrl + array[1].value + '/' + array[0].value,
        type: 'DELETE',
        success: function () {
            updateDesk();
            successNoty('Delete dish with id ' + array[0].value);
        }
    });
}

function profileForm() {
    var form = $('#user-update');
    $.get(profileUrl + 'get', function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
        form.find("input[name='password']").val('');
        form.modal();
    });
}

function deleteProfile() {
    var form = $('#details-user');
    $.ajax({
        url: profileUrl + 'delete',
        type: 'POST',
        data: form.serialize(),
        success: function () {
            $('#user-update').modal('hide');
            successNoty('delete profile');
            window.location.href = '/';
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
        timeout: 1000
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
