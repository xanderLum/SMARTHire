/*!
 * Start Bootstrap - Creative Bootstrap Theme (http://startbootstrap.com)
 * Code licensed under the Apache License v2.0.
 * For details, see http://www.apache.org/licenses/LICENSE-2.0.
 */

(function($) {
    "use strict"; // Start of use strict

    // jQuery for page scrolling feature - requires jQuery Easing plugin
    $('a.page-scroll').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top - 50)
        }, 1250, 'easeInOutExpo');
        event.preventDefault();
    });


    // Closes the Responsive Menu on Menu Item Click
    $('.navbar-collapse ul li a').click(function() {
        $('.navbar-toggle:visible').click();
    });

    // Fit Text Plugin for Main Header
    $("h1").fitText(
        1.2, {
            minFontSize: '35px',
            maxFontSize: '65px'
        }
    );

    // Offset for Main Navigation
    $('#mainNav').affix({
        offset: {
            top: 100
        }
    })

    // Initialize WOW.js Scrolling Animations
    new WOW().init();

})(jQuery); // End of use strict

function ShowMyDiv(Obj){
  var elements = document.getElementsByTagName('div');
 for (var i = 0; i < elements.length; i++) 
  if(elements[i].className=='tabcontent')
   elements[i].style.display= 'none';

 document.getElementById(Obj.rel).style.display= 'block';
 //------------------------------------

  var ul_el = document.getElementById('tab_ul');
  var li_el = ul_el.getElementsByTagName('li');
 for (var i = 0; i < li_el.length; i++) 
  li_el[i].className="";

 Obj.parentNode.className="selected";
}