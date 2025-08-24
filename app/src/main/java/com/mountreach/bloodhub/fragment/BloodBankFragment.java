package com.mountreach.bloodhub.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mountreach.bloodhub.R;

public class BloodBankFragment extends Fragment {

    private Spinner spinnerBloodGroup, spinnerBloodComponent;
    private CheckBox checkboxUseLocation;
    private EditText etState, etDistrict;
    private Button btnContinue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blood_bank, container, false);

        spinnerBloodGroup = view.findViewById(R.id.spinnerBloodGroup);
        spinnerBloodComponent = view.findViewById(R.id.spinnerBloodComponent);
        checkboxUseLocation = view.findViewById(R.id.checkboxUseLocation);
        etState = view.findViewById(R.id.etState);
        etDistrict = view.findViewById(R.id.etDistrict);
        btnContinue = view.findViewById(R.id.btnContinue);

        // Populate blood group spinner
        ArrayAdapter<CharSequence> adapterGroup = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.blood_groups,
                android.R.layout.simple_spinner_item);
        adapterGroup.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodGroup.setAdapter(adapterGroup);

        // Populate blood component spinner
        ArrayAdapter<CharSequence> adapterComponent = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.blood_components,
                android.R.layout.simple_spinner_item);
        adapterComponent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodComponent.setAdapter(adapterComponent);

        // Checkbox logic - disable state/district if using location
        checkboxUseLocation.setOnCheckedChangeListener((buttonView, isChecked) -> {
            etState.setEnabled(!isChecked);
            etDistrict.setEnabled(!isChecked);
            if (isChecked) {
                etState.setText("");
                etDistrict.setText("");
            }
        });

        // Continue button click
        btnContinue.setOnClickListener(v -> {
            String group = spinnerBloodGroup.getSelectedItem().toString();
            String component = spinnerBloodComponent.getSelectedItem().toString();
            boolean useLocation = checkboxUseLocation.isChecked();
            String state = etState.getText().toString().trim();
            String district = etDistrict.getText().toString().trim();

            // Validate selections
            if (group.equals("Select Blood Group")) {
                Toast.makeText(requireContext(), "Please select a Blood Group", Toast.LENGTH_SHORT).show();
                return;
            }

            if (component.equals("Select Blood Component")) {
                Toast.makeText(requireContext(), "Please select a Blood Component", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!useLocation) {
                if (TextUtils.isEmpty(state)) {
                    etState.setError("Enter State");
                    return;
                }
                if (TextUtils.isEmpty(district)) {
                    etDistrict.setError("Enter District");
                    return;
                }
            }

            // Success message
            StringBuilder result = new StringBuilder();
            result.append("Selected: ").append(group).append(", ").append(component);

            if (useLocation) {
                result.append("\nUsing Current Location");
            } else {
                result.append("\nState: ").append(state)
                        .append(", District: ").append(district);
            }

            Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_LONG).show();
        });

        return view;
    }
}

