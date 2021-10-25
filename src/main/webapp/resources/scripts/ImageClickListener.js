function checkHit(event) {
    let x = ((event.pageX - $('#graphic').offset().left - 15 - 150) / 30).toFixed(2);
    let y = ((event.pageY - $('#graphic').offset().top - 15 - 150) / -30).toFixed(2);

    PF("x-spinner").setValue(x);
    $('.y-input').val(y);
    $('input[type="submit"]').trigger('click');
}