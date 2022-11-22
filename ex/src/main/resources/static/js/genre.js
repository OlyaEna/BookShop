$('document').ready(function (){
    $('table #editButton').on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (genre, status){
            $('#idEdit').val(genre.id);
            $('#nameEdit').val(genre.name);
        });
        $('#editModal').modal();
    });
});