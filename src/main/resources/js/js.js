/**
 * Created by mercutio on 08.05.17.
 */
$(function () {
    $('.class').on('click', function (e) {
        e.preventDefault();
        $('.side-categories').toggleClass('hide');
    });
});
