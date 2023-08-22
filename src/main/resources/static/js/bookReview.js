function getTitle(bookTitle) {
    // console.log(bookTitle);
    alert(bookTitle);

    /*
    $.ajax({
        url: "/review",
        type: 'post',
        data: {
            title : bookTitle
        },
        success: function (data) {
            console.log(data);
            //alert(data);
            //location.href = data;
        },
        error: function () {
            console.log("제목 전달 오류");
        }
    });

     */

    let f = document.createElement('form');

    let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'title')
    obj.setAttribute('value', bookTitle);

    f.appendChild(obj);
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'review');
    document.body.appendChild(f);
    f.submit();

}

function getReview(book) {

    let f = document.createElement('form');

    let obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'title')
    obj.setAttribute('value', book);

    f.appendChild(obj);
    f.setAttribute('method', 'post');
    f.setAttribute('action', 'reviewList');
    document.body.appendChild(f);
    f.submit();

}