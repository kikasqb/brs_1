function rate(star,max){
  for (var i = max - 1; i >= star; i--) {
    $('#star-' + i).html('&#9733;');
  }
  for (var i = 0; i < star; i++) {
    $('#star-' + i).html('&#9734;');
  }
}
$.fn.stars = function() {
  return $(this).each(function() {
    if ($.isNumeric($(this).html())) {
      var val = parseFloat($(this).html());
      var size = Math.max(0, (Math.min(5, val))) * 32;
      var $span = $('<span />').width(size);
      $(this).html($span);
    }
  });
}
blackStar = '<span class="review-rate">&#9733;</span>';
$.fn.rate = function() {
  return $(this).each(function() {
    var val = parseInt($(this).html());
    html = '';
    for (var i = 0; i < val; i++) {
      html += blackStar;
    }
    $(this).html(html);
  });
}
function submit_books_search_form(){
  str = $('#search_books_form').serialize();
  $.ajax({
    type: "GET",
    url: '/books?' + str,
    dataType: "script",
    success: function(data){
    },
    error: function(error_message) {
      connect_failed.show();
    },
  });
}
function fadeOutMessage() {
  setTimeout(function() {
    $('#flash-messages').fadeOut('slow');
  },3000);
}
function markBookAs(bookID, mark){
  markIconElement = document.getElementById('mark-' + mark + '-' + bookID);
  markIconElement.className = "loader pull-right";
  $.ajax({
    type: 'POST',
    url: '/marks?read=' + mark + '&book_id=' + bookID,
    dataType: 'script',
    success: function(data){
    },
    error: function(error_message) {
      connect_failed.show();
      markIconElement.className = 'glyphicon glyphicon-ban-circle pull-right';
    },
  });
}
function init_config_book() {
  $('span.stars').stars();
  $('span.review-rate').rate();
  $('#search_books').keyup(function () {
    submit_books_search_form();
  });
  $('.mark-item').click(function(e) {
    e.stopPropagation();
  });
  jQuery('div.dropdown').hover(function() {
    jQuery(this).find('.dropdown-menu').stop(true, true).delay(500).fadeIn(500);
  }, function() {
    jQuery(this).find('.dropdown-menu').stop(true, true).delay(500).fadeOut(500);
  });
  fadeOutMessage();
}
$(document).ready(function() {
  init_config_book();
});
$( document ).on('turbolinks:load', function() {
  init_config_book();
})
