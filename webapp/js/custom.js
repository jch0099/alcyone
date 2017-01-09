// ------- PRELOADER -------//
$(window).load(function(){
    $('.preloader').fadeOut("slow"); // set duration in brackets    
});

/* HTML document is loaded. DOM is ready. 
-------------------------------------------*/
$(function(){

   // --------- HIDE MOBILE MENU AFTER CLIKING ON A LINK ------- //
    $('.navbar-collapse a').click(function(){
        $(".navbar-collapse").collapse('hide');
    });

  // --------- LIST IMAGE ----- //
  $('#edit a.fade-scale').nivoLightbox({
      effect: 'fadeScale',
});

  // ------- WOW ANIMATED ------ //
  wow = new WOW(
  {
    mobile: false
  });
  wow.init();

  // ------- GOOGLE MAP ----- //
  //loadGoogleMap();

  // ------- JQUERY PARALLAX ---- //
  function initParallax() {
    $('#index').parallax("100%", 0.3);
    $('#edit').parallax("100%", 0.3);
    $('#contact').parallax("100%", 0.1);

  }
  initParallax();

});

