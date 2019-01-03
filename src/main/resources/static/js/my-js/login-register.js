$('.role-radio-button').on('click', isUserRoleValid);

$('#firstname').on('input', delay(500, isFirstnameValid));

$('#lastname').on('input', delay(500, isLastnameValid));

$('#register-email').on('input', delay(1000, isEmailValid, [false]));

$('#register-password').on('input', delay(500, isRegisterPasswordValid, [false]));

$('#register-password').on('change', function () {
    $("#password-requirements").find('li').addClass('invalid');
});

$('#register-password').on('focus', function () {
    $("#register-password-error-message-empty").hide();
    $("#register-password-error-message-invalid").hide();
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
    var emptyMessageErrorSelector = '#register-password-error-message-empty';
    var invalidMessageErrorSelector = '#register-password-error-message-invalid';

    var isNotEmptyField = isNotEmpty(selector);
    var isCorrectFormat = isPasswordFormatCorrect(selector);
    if (!isNotEmptyField) {
        $(emptyMessageErrorSelector).show();
        makeFieldInvalid(selector);
    } else {
        if (!isCorrectFormat) {
            $(invalidMessageErrorSelector).show();
            makeFieldInvalid(selector);
        }
    }
    return (isNotEmptyField && isCorrectFormat);
}

function isPasswordFormatCorrect(selector) {
    var element = $(selector);
    var value = element.val();
    var lengthCondition = value.length > 8;
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

function isRegisterPasswordValid() {
    var selector = '#register-password';
    var emptyMessageErrorSelector = '#register-password-error-message-empty';
    var invalidMessageErrorSelector = '#register-password-error-message-invalid';

    var isNotEmptyField = isNotEmpty(selector);
    var isCorrectFormat = isPasswordFormatCorrect(selector);
    var condition = isNotEmptyField && isCorrectFormat;
    if (isNotEmptyField || isCorrectFormat) {
        makeFieldValid(selector);
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
        $("#password-requirements").hide();
        selector = '#register-email';
        emptyMessageErrorSelector = '#register-email-error-message-empty';
        invalidMessageErrorSelector = "#register-email-error-message-invalid";
    }
    var isNotEmptyField = isNotEmpty(selector);
    var isCorrectFormat = isEmailFormatCorrect(selector);
    if (!isNotEmptyField) {
        $(emptyMessageErrorSelector).show();
        makeFieldInvalid(selector);
    } else {
        if (!isCorrectFormat) {
            $(invalidMessageErrorSelector).show();
            makeFieldInvalid(selector);
        }
    }
    return (isNotEmptyField && isCorrectFormat);
}

function isEmailFormatCorrect(selector) {
    var element = $(selector);
    return (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(element.val()));
    //     / .. /	All regular expressions start and end with forward slashes.
    //     ^	    Matches the beginning of the string or line.
    //     \w+	    Matches one or more word characters including the underscore. Equivalent to [A-Za-z0-9_].
    //     [\.-]	\ Indicates that the next character is special and not to be interpreted literally.
    //     .-       Matches character . or -.
    //     ?	    Matches the previous character 0 or 1 time. Here previous character is [.-].
    //     \w+	    Matches 1 or more word characters including the underscore. Equivalent to [A-Za-z0-9_].
    //     *	    Matches the previous character 0 or more times.
    //     ([.-]?\w+)*	Matches 0 or more occurrences of [.-]?\w+.
    //     \w+([.-]?\w+)*	The sub-expression \w+([.-]?\w+)* is used to match the username in the email. It begins with at least one or more word characters including the underscore, equivalent to [A-Za-z0-9_]. , followed by . or - and . or - must follow by a word character (A-Za-z0-9_).
    //     @	    It matches only @ character.
    //     \w+([.-]?\w+)*	It matches the domain name with the same pattern of user name described above..
    //     \.\w{2,3}	It matches a . followed by two or three word characters, e.g., .edu, .org, .com, .uk, .us, .co etc.
    //     +	    The + sign specifies that the above sub-expression shall occur one or more times, e.g., .com, .co.us, .edu.uk etc.
    //     $	    Matches the end of the string or line.
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