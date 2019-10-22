var posDrug = document.getElementById("posDrug").value;
var negDrug = document.getElementById("negDrug").value;
var posGun = document.getElementById("posGun").value;
var negGun = document.getElementById("negGun").value;
var posAlcohol = document.getElementById("posAlcohol").value;
var negAlcohol = document.getElementById("negAlcohol").value;
var posProfane = document.getElementById("posProfane").value;
var negProfane = document.getElementById("negProfane").value;
var posGS = document.getElementById("posGS").value;
var negGS = document.getElementById("negGS").value;
var posCharity = document.getElementById("posCharity").value;
var negCharity = document.getElementById("negCharity").value;
var posSex = document.getElementById("posSex").value;
var negSex = document.getElementById("negSex").value;
var posPolitics = document.getElementById("posPolitics").value;
var negPolitics = document.getElementById("negPolitics").value;
var header = $('.stats__header');
var bar  = $('.stats__item-bar');
var nums = $('.stats__item-num');
var overlay = $('.stats__overlay');
var back = $('.stats__overlay-back');
var isOpen = false;

var vCategory = $('#category');
var vAvg = $('#avg');
var vNegative = $('#negative'); 
var vPositive = $('#positive');

$(document).on('ready', function() {
  entrance();
});

bar.on('click', showOverlay);
back.on('click', showOverlay);

function entrance() {
  bar.addClass('active');
  header.addClass('active');
  header.on('transitionend webkitTransitionend', function() {
    nums.css('opacity', '1');
    bar.css('transition-delay', '0');
    header.off('transitionend webkitTransitionend');
  });
}

function showOverlay() {
  if (!isOpen) {
    overlay.addClass('active').removeAttr('style');
    bar.css('transition', 'all 0.4s cubic-bezier(0.86, 0, 0.07, 1)')
    .removeClass('active');
    header.removeClass('active');
    nums.css('opacity', '0');
    isOpen = true;
    
   updateInfo($(this).parent().index());
  } else {
    overlay.css('transition', 'all 0.4s cubic-bezier(0.755, 0.05, 0.855, 0.06)').removeClass('active');
    bar.addClass('active').removeAttr('style');
    header.addClass('active');
    nums.css('opacity', '1');
    isOpen = false;
  }
}

var data = [
  {
    category: 'Illegal Drug',
    positive: posDrug,
    negative: negDrug,
    avg:  "0.9"
    
  },
  {
	category: 'Alcohol Beverage',
    positive: posAlcohol,
    negative: negAlcohol,
    avg: '0.7'
    
  },
  {
	category: 'Profanity',
    positive: posProfane,
    negative: negProfane,
    avg: '0.69'
    
  },
  {
	category: 'Grammar and Spell Checker',
    positive: posGS,
    negative: negGS,
    avg: '0.40'
    
  },
  {
	category: 'Charity',
    positive: posCharity,
    negative: negCharity,
    avg: '0.48'
    
  },
  {
	category: 'Politics',
    positive: posPolitics,
    negative: negPolitics,
    avg: '0.66'
    
  },
  {
	category: 'Sex Post',
    positive: posSex,
    negative: negSex,
    avg: '0.65'
    
  },
  {
	category: 'Possession Firearm',
    positive: posGun,
    negative: negGun,
    avg: '0.66'
    
  }
];

function updateInfo(index) {
  vCategory.text(data[index].category);
  vAvg.text(data[index].avg);
  vPositive.text(data[index].positive);
  vNegative.text(data[index].negative);
}