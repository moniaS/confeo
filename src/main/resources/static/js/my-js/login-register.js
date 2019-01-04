$('.role-radio-button').on('click', isUserRoleValid);

$('#firstname').on('input', delay(500, validateFirstname));

$('#lastname').on('input', delay(500, validateLastname));

$('#register-email').on('input', delay(1000, validateEmail, [false]));

$('#register-password').on('input', delay(500, validateRegisterPassword));

$('#register-password').on('change', function () {
    $("#password-requirements").find('li').addClass('invalid');
});

$('#register-password').on('focus', function () {
    $("#password-requirements").show();
});

$('#login-email').on('input', delay(1000, isEmailValid, [true]));

$('#login-password').on('input', delay(1000, isLoginPasswordValid));

$(".toggle-password").on("click", function () {
    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
});

/*$('#register-checkbox').change(function() {
	console.log('halo');
	if ($(this).checked === true) {
        $('.terms-of-use-block .register-checkbox-label').css({"border": "none"});
        $("#terms-error-message").hide();
	}
}));*/

markUserRoleRadioButton();

// *******************REGISTER FORM*********************

function markUserRoleRadioButton() {
    $(".register-blocks input:radio:first").attr('checked', true);
}

function isUserRoleValid() {
    if ($('input[name="role"]:checked').length === 0) {
        $('#role-error-message').show();
        $('.user-role-radio-button').css({
            "border-color": "red",
            "border-width": "1px",
            "border-style": "solid"
        });
    } else {
        $('#role-error-message').hide();
        $('.user-role-radio-button').css({"border": "none"});
    }
}



function areTermsOfUseSumbitted() {
    if (document.getElementById("register-checkbox").checked === true) {
        $('.terms-of-use-block .register-checkbox-label').css({"border": "none"});
        $("#terms-error-message").hide();
        return true;
    } else {
        $('.terms-of-use-block .register-checkbox-label').css({
            "border-color": "red",
            "border-width": "1px",
            "border-style": "solid"
        });
        $("#terms-error-message").show();
        return false;
    }
}

function validateFirstname() {
    var isValid = isFirstnameValid();
    if (!isValid) {
        $("#firstname-error-message").show();
    }
    return isValid;
}

function isFirstnameValid() {
    var selector = '#firstname';
    var isValid = isNotEmpty(selector);
    if (!isValid) {
        makeFieldInvalid(selector);
    } else {
        makeFieldValid(selector);
        $("#firstname-error-message").hide();
    }
    return isValid;
}

function validateLastname() {
    var isValid = isLastnameValid();
    if (!isValid) {
        $("#lastname-error-message").show();
    }
    return isValid;
}

function isLastnameValid() {
    var selector = '#lastname';
    var isValid = isNotEmpty(selector);
    if (!isValid) {
        makeFieldInvalid(selector);
    } else {
        makeFieldValid(selector);
        $("#lastname-error-message").hide();
    }
    return isValid;
}

function validateRegisterPassword() {
    var selector = '#register-password';
    var isNotEmptyField = isNotEmpty(selector);
    var isCorrectFormat = isPasswordFormatCorrect(selector);
    if (!isNotEmptyField) {
        makeFieldInvalid(selector);
    } else {
        if (!isCorrectFormat) {
            makeFieldInvalid(selector);
        }
    }
    return (isNotEmptyField && isCorrectFormat);
}

function isPasswordFormatCorrect(selector) {
    var element = $(selector);
    var value = element.val();
    var lengthCondition = value.length >= 8;
    var lowerLetterCondition = value.match(/[a-z]/);
    var capitalLetterCondition = value.match(/[A-Z]/);
    var numberCondition = value.match(/\d/);

    if ( !lengthCondition ) {
        $('#length').removeClass('valid').addClass('invalid');
    } else {
        $('#length').removeClass('invalid').addClass('valid');
    }

    //validate lower letter
    if (lowerLetterCondition) {
        $('#lower-letter').removeClass('invalid').addClass('valid');
    } else {
        $('#lower-letter').removeClass('valid').addClass('invalid');
    }

    //validate capital letter
    if (capitalLetterCondition) {
        $('#capital-letter').removeClass('invalid').addClass('valid');
    } else {
        $('#capital-letter').removeClass('valid').addClass('invalid');
    }

    //validate number
    if (numberCondition) {
        $('#number').removeClass('invalid').addClass('valid');
    } else {
        $('#number').removeClass('valid').addClass('invalid');
    }

    return lengthCondition && lowerLetterCondition && capitalLetterCondition && numberCondition;
}

function validateRegisterForm() {
    var isFirstnameValid = validateFirstname();
    var isLastnameValid = validateLastname();
    var isEmailValid = validateEmail(false);
    var isPasswordValid = validateRegisterPassword();
    var areTermsSumbitted = areTermsOfUseSumbitted();
    return (isFirstnameValid && isLastnameValid && isPasswordValid && areTermsSumbitted
        && isEmailValid);
}

// *******************LOGIN FORM*********************

function validateLoginPassword() {
    var selector = '#login-password';
    var emptyMessageErrorSelector = '#login-password-error-message-empty';

    var isNotEmptyField = isNotEmpty(selector);
    if (!isNotEmptyField) {
        $(emptyMessageErrorSelector).show();
        makeFieldInvalid(selector);
    } else {
        makeFieldValid(selector);
    }
}

function isLoginPasswordValid() {
    var selector = '#login-password';
    var emptyMessageErrorSelector = '#login-password-error-message-empty';
    var isNotEmptyField = isNotEmpty(selector);
    if (isNotEmptyField) {
        makeFieldValid(selector);
        $(emptyMessageErrorSelector).hide();
    } else {
        makeFieldInvalid(selector);
    }
    return isNotEmptyField;
}

function validateLoginForm() {
    var isLoginValid = validateEmail(true);
    var isPasswordValid = validateLoginPassword();
    return (isLoginValid && isPasswordValid);
}

//********************COMMON FUNCTIONS FOR BOTH FORMS*******************

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

function isNotEmpty(selector) {
    var notEmptyField = $(selector).val().trim() != '';
    if (notEmptyField) {
        makeFieldValid(selector)
    } else {
        makeFieldInvalid(selector)
    }
    return notEmptyField;
}

function makeFieldValid(selector) {
    $(selector).removeClass('invalid-field');
    $(selector).addClass('valid-field');
    $(selector).parent().find('img.invalid-icon').hide();
    $(selector).parent().find('img.valid-icon').show();
}

function makeFieldInvalid(selector) {
    $(selector).removeClass('valid-field');
    $(selector).addClass('invalid-field');
    $(selector).parent().find('img.valid-icon').hide();
    $(selector).parent().find('img.invalid-icon').show();
}

function validateEmail(isLoginForm) {
    var selector, emptyMessageErrorSelector, invalidMessageErrorSelector;
    if (isLoginForm) {
        selector = '#login-email';
        emptyMessageErrorSelector = '#login-email-error-message-empty';
        invalidMessageErrorSelector = "#login-email-error-message-invalid";
    } else {
        selector = '#register-email';
        emptyMessageErrorSelector = '#register-email-error-message-empty';
        invalidMessageErrorSelector = "#register-email-error-message-invalid";
    }
    var isNotEmptyField = isNotEmpty(selector);
    var isCorrectFormat = isEmailFormatCorrect(selector);
    if (!isNotEmptyField) {
        $(invalidMessageErrorSelector).hide();
        $(emptyMessageErrorSelector).show();
        makeFieldInvalid(selector);
    } else {
        if (!isCorrectFormat) {
            $(emptyMessageErrorSelector).hide();
            $(invalidMessageErrorSelector).show();
            makeFieldInvalid(selector);
        } else {
            $(emptyMessageErrorSelector).hide();
            $(invalidMessageErrorSelector).hide();
            makeFieldValid(selector);
        }
    }
    return (isNotEmptyField && isCorrectFormat);
}

function isEmailFormatCorrect(selector) {
    var element = $(selector);
    var sQtext = '[^\\x0d\\x22\\x5c\\x80-\\xff]';
    var sDtext = '[^\\x0d\\x5b-\\x5d\\x80-\\xff]';
    var sAtom = '[^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+';
    var sQuotedPair = '\\x5c[\\x00-\\x7f]';
    var sDomainLiteral = '\\x5b(' + sDtext + '|' + sQuotedPair + ')*\\x5d';
    var sQuotedString = '\\x22(' + sQtext + '|' + sQuotedPair + ')*\\x22';
    var sDomain_ref = sAtom;
    var sSubDomain = '(' + sDomain_ref + '|' + sDomainLiteral + ')';
    var sWord = '(' + sAtom + '|' + sQuotedString + ')';
    var sDomain = sSubDomain + '(\\x2e' + sSubDomain + ')*';
    var sLocalPart = sWord + '(\\x2e' + sWord + ')*';
    var sAddrSpec = sLocalPart + '\\x40' + sDomain; // complete RFC822 email address spec
    var sValidEmail = '^' + sAddrSpec + '$'; // as whole string

    var reValidEmail = new RegExp(sValidEmail);

    if (reValidEmail.test(element.val())) {
        return true;
    }

    return false;
}

function isEmailValid(isLoginForm) {
    var selector, emptyMessageErrorSelector, invalidMessageErrorSelector;
    if (isLoginForm) {
        selector = '#login-email';
        emptyMessageErrorSelector = '#login-email-error-message-empty';
        invalidMessageErrorSelector = "#login-email-error-message-invalid";
    } else {
        selector = '#register-email';
        emptyMessageErrorSelector = '#register-email-error-message-empty';
        invalidMessageErrorSelector = "#register-email-error-message-invalid";
    }
    var isNotEmptyField = isNotEmpty(selector);
    var isCorrectFormat = isEmailFormatCorrect(selector);
    var condition = isNotEmptyField && isCorrectFormat;
    if (isNotEmptyField || isCorrectFormat) {
        $(emptyMessageErrorSelector).hide();
        $(invalidMessageErrorSelector).hide();
        if (condition) {
            makeFieldValid(selector);
        } else {
            makeFieldInvalid(selector);
        }
    } else {
        makeFieldInvalid(selector);
    }
    return condition;
}