package com.gcpcx.formvalidation.domain;

import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gcpcx.formvalidation.data.model.Form;
import com.gcpcx.formvalidation.data.model.Validation;
import com.gcpcx.formvalidation.data.repository.INetworkRepository;
import com.gcpcx.formvalidation.data.service.reponse.FormResponse;
import com.gcpcx.formvalidation.di.LocalDependency;
import com.gcpcx.formvalidation.utils.EValidation;

import java.util.Locale;


public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<Validation> _validationName = new MutableLiveData<>();
    private final MutableLiveData<Validation> _validationEmail = new MutableLiveData<>();
    private final MutableLiveData<Validation> _validationMobileNumber = new MutableLiveData<>();
    private final MutableLiveData<Validation> _validationAge = new MutableLiveData<>();
    private final MutableLiveData<FormResponse> _formResponse = new MutableLiveData<>();

    public LiveData<FormResponse> formResponse = _formResponse;
    public LiveData<Validation> validationName = _validationName;
    public LiveData<Validation> validationEmail = _validationEmail;
    public LiveData<Validation> validationMobileNumber = _validationMobileNumber;
    public LiveData<Validation> validationAge = _validationAge;

    private final INetworkRepository.INetworkCallback networkCallback = new INetworkRepository.INetworkCallback() {
        @Override
        public void onSuccessResponse(FormResponse formResponse) {
            _formResponse.postValue(formResponse);
        }

        @Override
        public void onFailedResponse(FormResponse formResponse) {
            _formResponse.postValue(formResponse);
        }
    };

    private final INetworkRepository networkRepository = LocalDependency.getNetworkRepository(networkCallback);

    public void submitForm(Form form) {
        validateName(form.getFullName());
        validateEmail(form.getEmail());
        validateMobilePhone(form.getMobileNumber());
        validateAge(form.getAge());

        networkRepository.getFormResponse();

    }


    private void validateName(String fullName) {
        String currentFullName = fullName.trim().toLowerCase(Locale.ROOT);
        Validation validation = customValidation(
                "Full name must text and characters like comma and period.",
                EValidation.NAME
        );

        if (!currentFullName.isEmpty() && !validateSpecialCharacters(currentFullName)) {
            validation.setMessage("");

        }
        _validationName.postValue(validation);
    }

    private void validateEmail(String email) {
        Validation validation = customValidation(
                "Wrong Email format", EValidation.EMAIL
        );

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            validation.setMessage("");

        }
        _validationEmail.postValue(validation);
    }

    private void validateMobilePhone(String mobilePhone) {
        Validation validation = customValidation(
                "Invalid mobile phone format.",
                EValidation.MOBILE_NUMBER
        );

        if (!mobilePhone.isEmpty()) {
            if ((mobilePhone.startsWith("09") && mobilePhone.length() == 11) ||
                    (mobilePhone.startsWith("63") && mobilePhone.length() == 12) ||
                    (mobilePhone.startsWith("+63") && mobilePhone.length() == 13) ||
                    (mobilePhone.startsWith("9") && mobilePhone.length() == 10)) {
                validation.setMessage("");

            }
        }
        _validationMobileNumber.postValue(validation);
    }

    private void validateAge(String age) {
        Validation validation = customValidation(
                "Age must be 18 and above.",
                EValidation.AGE
        );

        try {
            if ((age.isEmpty() ? 0 : Integer.parseInt(age)) > 17) {
                validation.setMessage("");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        _validationAge.postValue(validation);
    }

    private Boolean validateSpecialCharacters(String fullName) {
        boolean hasSpecial = false;
        for (Character cr : fullName.toCharArray()) {
            String special = "!@#$%&*()'+-/:;<=>?[]^_`{|}0123456789";
            String current = Character.toString(cr);
            if (special.contains(current)) {
                hasSpecial = true;
                break;
            }
        }
        return hasSpecial;
    }

    private Validation customValidation(
            String message,
            EValidation code
    ) {
        Validation validation = new Validation();
        validation.setCode(code);
        validation.setMessage(message);
        return validation;
    }

}
