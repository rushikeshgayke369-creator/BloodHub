package com.mountreach.bloodhub.fragment;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import android.app.DatePickerDialog;

import com.mountreach.bloodhub.R;

public class RequestForBloodFragment extends Fragment {

    private AutoCompleteTextView bloodTypeDropdown, bloodGroupDropdown, unitsDropdown;
    private EditText patientNameEditText, mobileNumberEditText, requiredDateEditText, locationEditText, notesEditText;
    private CheckBox termsCheckBox;
    private Button sendRequestButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_request_for_blood, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bloodTypeDropdown = view.findViewById(R.id.bloodTypeDropdown);
        bloodGroupDropdown = view.findViewById(R.id.bloodGroupDropdown);
        unitsDropdown = view.findViewById(R.id.unitsDropdown);
        patientNameEditText = view.findViewById(R.id.patientNameEditText);
        mobileNumberEditText = view.findViewById(R.id.mobileNumberEditText);
        requiredDateEditText = view.findViewById(R.id.requiredDateEditText);
        locationEditText = view.findViewById(R.id.locationEditText);
        notesEditText = view.findViewById(R.id.notesEditText);
        termsCheckBox = view.findViewById(R.id.termsCheckBox);
        sendRequestButton = view.findViewById(R.id.sendRequestButton);

        setupDropdowns();

        setupDatePicker();

        sendRequestButton.setOnClickListener(v -> {
            if (validateInputs()) {
                Toast.makeText(getContext(), "Request submitted successfully!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDropdowns() {
        String[] bloodTypes = new String[]{"Whole Blood", "Platelets", "Plasma"};
        ArrayAdapter<String> bloodTypeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, bloodTypes);
        bloodTypeDropdown.setAdapter(bloodTypeAdapter);

        String[] bloodGroups = new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-", "A1+", "A1-", "A2+", "A2-", "A1B+"};
        ArrayAdapter<String> bloodGroupAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, bloodGroups);
        bloodGroupDropdown.setAdapter(bloodGroupAdapter);

        String[] units = new String[]{"1 Unit", "2 Units", "3 Units", "4 Units"};
        ArrayAdapter<String> unitsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, units);
        unitsDropdown.setAdapter(unitsAdapter);
    }

    private void setupDatePicker() {
        requiredDateEditText.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    (view1, year1, monthOfYear, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                        requiredDateEditText.setText(selectedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });
    }

    private boolean validateInputs() {
        if (patientNameEditText.getText().toString().isEmpty() ||
                mobileNumberEditText.getText().toString().isEmpty() ||
                bloodGroupDropdown.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!termsCheckBox.isChecked()) {
            Toast.makeText(getContext(), "Please agree to the Terms and Privacy Policy", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}