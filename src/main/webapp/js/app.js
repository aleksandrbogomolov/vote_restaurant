$('.scroll-top').click(function () {
    $('body,html').animate({scrollTop: 0}, 1000);
});

$('.collapse').collapse('toggle');

function makeEditable() {
    $('.vote').click(function () {
        addVote($(this).attr('id'));
    });

    $('.voteClear').click(function () {
        clearVote();
    });
}

function updateDesk() {
    $.get(restaurantUrl, function (data) {
        $('#datatable').hide().html(data).fadeIn('fast');
    });

}

function addVote(id) {
    $.ajax({
        url: voteUrl + 'add/' + id,
        type: 'POST',
        success: function () {
            updateDesk();
        }
    });
}

function clearVote() {
    $.ajax({
        url:voteUrl + 'clear',
        type:'DELETE',
        success: function () {
            updateDesk();
        }
    })
}
