// UZYTY TEMPLATE DO WIDOKU INGERUJE W KLASY CHECKBOXA PRZEZ CO NIE MOGE SPRAWDZIC CZY CHECKBOX JEST ZAZNACZONY CZY NIE I W ZALEZNOSCI OD TEGO USTAWIC WIDOCZNOSC DIVA price-form-group
/*if($('#isFree').checked) {
	console.log('halo1');
    $("#price-form-group").show();
} else {
	console.log('halo2');
    $("#price-form-group").hide();
}

$('.icheckbox_square-yellow').change(function() {
	console.log('halo');
    //$("#price-form-group").toggle(this.checked);
});
*/
$( document ).ready(function() {

    console.log( "ready!" );
});

//walidacja pol po zmianie
$(function() {
	
	$('#name').change(function() {
		validateName('#name');
	});
	
	$('#startDate').change(function() {
		validateStartDate('#startDate');
		validateEndDate('#endDate');
	});
	
	$('#endDate').change(function() {
		validateEndDate('#endDate');
		validateStartDate('#startDate');
	});
	
	$('#type').change(function() {
		validateType('#type');
	});
	
	$('#category').change(function() {
		validateCategory('#category');
	})
	
	$('#description').change(function() {
		validateDescription('#description');
	})
	
	$('#street').change(function() {
		validateStreet('#street');
	})
	
	$('#street-number').change(function() {
		validateStreetNumber('#street-number');
	})
	
	$('#city').change(function() {
		validateCity('#city');
	})
	
	$('#maxParticipants').change(function() {
		validateMaxParticipants('#maxParticipants');
	})
	
});

function validateName(selector){
	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#name-error-message').show();
	} else {
		makeFieldValid(selector);
		$('#name-error-message').hide();
	}
}

//data musi byc w przyszlosci i musi byc mniejsza od daty zakonczenia
function validateStartDate(selector){
	var invalid = false;
	if ($(selector).val() == ''){
		invalid = true;
	}
	
	var now = new Date();
	var startDate = new Date($(selector).val());
	var endDate = new Date($('#endDate').val());
	if (startDate <= now || startDate > endDate) {
		invalid = true;
	}
	
	if (invalid) {
		makeFieldInvalid(selector);
		$('#startDate-error-message').show();
	} else {
		makeFieldValid(selector);
		$('#startDate-error-message').hide();
	}
	
}

//data zakonczenia musi byc wieksza od daty rozpoczecia
function validateEndDate(selector){
	var invalid = false;
	if ($(selector).val() == ''){
		invalid = true;
	}
	
	var now = new Date();
	var startDate = new Date($('#startDate').val());
	var endDate = new Date($(selector).val());
	if (endDate <= now || endDate < startDate) {
		invalid = true;
	}
	
	if (invalid) {
		makeFieldInvalid(selector);
		$('#endDate-error-message').show();
	} else {
		makeFieldValid(selector);
		$('#endDate-error-message').hide();
	}
}

//sprawdz czy pole niepuste (choc teoretycznie nigdy nie bedzie puste)
function validateType(selector){
	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#type-error-message').show();
	} else {
		makeFieldValid(selector);
		$('#type-error-message').hide();
	}
}

//sprawdz czy pole niepuste (choc teoretycznie nigdy nie bedzie puste)
function validateCategory(selector){
	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#category-error-message').show();
	} else {
		makeFieldValid(selector);
		$('#cateogry-error-message').hide();
	}
}

//pole moze byc puste ale nie moze byc wiecej niz 250 znakow
function validateDescription(selector){
	var text_length = $(selector).val().length;
	if (text_length > 250){
		makeFieldInvalid(selector);
		$('#description-error-message').show();
	} else {
		makeFieldValid(selector);
		$('#description-error-message').hide();
	}
}

function validateStreet(){
	
}

function validateStreetNumber(){
	
}

function validateCity(){
	
}

function validateMaxParticipants(){
	
}

function makeFieldValid(selector) {
    $(selector).removeClass('invalid-field');
    $(selector).addClass('valid-field');
}

function makeFieldInvalid(selector) {
    $(selector).removeClass('valid-field');
    $(selector).addClass('invalid-field');
}

$(document).ready(function() {
    $('#description').keyup(function() {
        var text_length = $('#description').val().length;
        $('#chars_description').html(text_length + ' / 250');
    });
});

	
	