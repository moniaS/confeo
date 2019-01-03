//walidacja pol po zmianie
$(function() {
	
	$('#name').change(function() {
		validateName('#name');
	});
	
	$('#startDate').change(function() {
		validateStartDate('#startDate');
		if ($('#endDate').val() != '') {
			validateEndDate('#endDate');
		}
	});
	
	$('#endDate').change(function() {
		validateEndDate('#endDate');
		if ($('#startDate').val() != '') {
			validateStartDate('#startDate');
		}
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
		return false;
	} else {
		makeFieldValid(selector);
		$('#startDate-error-message').hide();
		return true;
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
		return false;
	} else {
		makeFieldValid(selector);
		$('#endDate-error-message').hide();
		return true;
	}
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




	
	