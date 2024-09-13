 $(document).ready(function(){
    $('#login').hide();

    $('#open_close').click(function(){
        $('#login').slideToggle();
        $('#registro').hide();
    })
 })

 $(document).ready(function(){
    $('#registro').hide();

    $('#open_close2').click(function(){
        $('#registro').slideToggle();
        $('#login').hide();
    })
 })
