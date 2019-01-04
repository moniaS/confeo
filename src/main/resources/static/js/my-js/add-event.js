//walidacja pol po zmianie
	
$(document).ready(function() {
	$('#name').on('input', delay(500, function() {
		validateName('#name');
	}));
	
	$('#startDate').on('input', delay(500, function() {
		validateStartDate('#startDate');
		if ($('#endDate').val() != '') {
			validateEndDate('#endDate');
		}
	}));
	
	$('#endDate').on('input', delay(500, function() {
		validateEndDate('#endDate');
		if ($('#startDate').val() != '') {
			validateStartDate('#startDate');
		}
	}));
	
	$('#type').on('input', delay(500, function() {
		validateType('#type');
	}));
	
	$('#category').on('input', delay(500, function() {
		validateCategory('#category');
	}));
	
	$('#description').on('input', delay(500, function() {
		validateDescription('#description');
	}));
	
	$('#street').on('input', delay(500, function() {
		validateStreet('#street');
	}));
	
	$('#street-number').on('input', delay(500, function() {
		validateStreetNumber('#street-number');
	}));
	
	$('#city').on('input', delay(500, function() {
		validateCity('#city');
	}));
	
	$('#maxParticipants').on('input', delay(500, function() {
		validateMaxParticipants('#maxParticipants');
	}));
});

function validateForm(){
	var formIsValid = true;
	
	if (validateMaxParticipants('#maxParticipants') == false){
		$('#maxParticipants').focus();
		formIsValid = false;
	}
	if (validateCity('#city') == false ){
		$('#city').focus();
		formIsValid = false;
	}
	if (validateStreetNumber('#street-number') == false ){
		$('#street-number').focus();
		formIsValid = false;
	}
	if (validateStreet('#street') == false ){
		$('#street').focus();
		formIsValid = false;
	}
	if (validateDescription('#description') == false ){
		$('#description').focus();
		formIsValid = false;
	}
	if (validateCategory('#category') == false ){
		$('#category').focus();
		formIsValid = false;
	}
	if (validateType('#type') == false ){
		$('#type').focus();
		formIsValid = false;
	}
	if (validateEndDate('#endDate') == false ){
		$('#endDate').focus();
		formIsValid = false;
	}
	if (validateStartDate('#startDate') == false ){
		$('#startDate').focus();
		formIsValid = false;
	}
	if (validateName('#name') == false ){
		$('#name').focus();
		formIsValid = false;
	}

	

	return formIsValid;
}

function validateName(selector){
	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#name-error-message').show();
		return false;
	} else {
		makeFieldValid(selector);
		$('#name-error-message').hide();
		return true;
	}
}

//data musi byc w przyszlosci i musi byc mniejsza od daty zakonczenia
function validateStartDate(selector){
	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#startDate-error-message1').show();
		return false;
	}
	
	var now = new Date();
	var startDate = new Date($(selector).val());
	var endDate = new Date($('#endDate').val());
	if (startDate <= now || startDate > endDate) {
		makeFieldInvalid(selector);
		$('#startDate-error-message2').show();
		return false;
	}
		
	makeFieldValid(selector);
	$('#startDate-error-message1').hide();
	$('#startDate-error-message2').hide();
	return true;
	
}

//data zakonczenia musi byc wieksza od daty rozpoczecia
function validateEndDate(selector){
	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#endDate-error-message1').show();
		return false;
	}
	
	var now = new Date();
	var startDate = new Date($('#startDate').val());
	var endDate = new Date($(selector).val());
	if (endDate <= now || endDate < startDate) {
		makeFieldInvalid(selector);
		$('#endDate-error-message2').show();
		return false;
	}
	
	makeFieldValid(selector);
	$('#endDate-error-message1').hide();
	$('#endDate-error-message2').hide();
	return true;
	
}

//sprawdz czy pole niepuste (choc teoretycznie nigdy nie bedzie puste)
function validateType(selector){

	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#type-error-message').show();
		return false;
	} else {
		makeFieldValid(selector);
		$('#type-error-message').hide();
		return true;
	}
}

//sprawdz czy pole niepuste (choc teoretycznie nigdy nie bedzie puste)
function validateCategory(selector){

	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#category-error-message').show();
		return false;
	} else {
		makeFieldValid(selector);
		$('#cateogry-error-message').hide();
		return true;
	}
}

//pole moze byc puste ale nie moze byc wiecej niz 250 znakow
function validateDescription(selector){

	var text_length = $(selector).val().length;
	if (text_length > 250){
		makeFieldInvalid(selector);
		$('#description-error-message').show();
		return false;
	} else {
		makeFieldValid(selector);
		$('#description-error-message').hide();
		return true;
	}
}

function validateStreet(selector){

	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#street-error-message').show();
		return false;
	} else {
		makeFieldValid(selector);
		$('#street-error-message').hide();
		return true;
	}
}

function validateStreetNumber(selector){

	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#streetNumber-error-message').show();
		return false;
	} else {
		makeFieldValid(selector);
		$('#streetNumber-error-message').hide();
		return true;
	}
}

function validateCity(selector){
	if ($(selector).val() == ''){
		makeFieldInvalid(selector);
		$('#city-error-message').show();
		return false;
	} else {
		makeFieldValid(selector);
		$('#city-error-message').hide();
		$('#city-warning-message').hide();
		return true;
	}
}

function validateMaxParticipants(selector){
	if ($(selector).val() == '' || $(selector).val() < 1){
		makeFieldInvalid(selector);
		$('#maxParticipants-error-message').show();
		return false;
	} else {
		makeFieldValid(selector);
		$('#maxParticipants-error-message').hide();
		return true;
	}
}

function makeFieldValid(selector) {
    $(selector).removeClass('invalid-field');
    $(selector).parent().find('img.invalid-icon').hide();
    //$(selector).addClass('valid-field');
}

function makeFieldInvalid(selector) {
    //$(selector).removeClass('valid-field');
    $(selector).parent().find('img.invalid-icon').show();
    $(selector).addClass('invalid-field');
}

$(document).ready(function() {
    $('#description').keyup(function() {
        var text_length = $('#description').val().length;
        $('#chars_description').html(text_length + ' / 250');
    });
    
 
});

$(document).ready(function() {
	$('#city').on('input', function() {
		if ($(this).val().length == 4) {
			$.getJSON('/cities/search', {
				cityName : $(this).val(),
				ajax : 'true'
			})
				.done(function(data) {	
					var selectNode = document.getElementById("cities");
					while (selectNode.firstChild) {
						selectNode.removeChild(selectNode.firstChild);
					}
					$.each(data, function(i, item) {
						$('#cities').append($('<option>').attr("value", item).text(item))
					});
				})
				.error(function(jqXHR, textStatus, errorThrown) {
			        console.log("error " + textStatus);
			    });
		}
	});
});

$(document).ready(function() {
	$('#city').on('change', function() {
		console.log('sprawdzam miasto');
		$.getJSON('/cities/check-if-exists', { cityName: $(this).val() })
			.done(function(data) {
				if (data === false){
					$('#city-warning-message').show();
				}
			});
	});
});

function delay(ms, callback, params) {
    var timer = 0;
    return function () {
        var context = this;
        clearTimeout(timer);
        timer = setTimeout(function () {
            callback.apply(context, params);
        }, ms || 0);
    };
}	