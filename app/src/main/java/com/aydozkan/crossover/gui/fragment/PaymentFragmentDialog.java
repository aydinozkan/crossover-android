package com.aydozkan.crossover.gui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aydozkan.crossover.R;
import com.braintreepayments.cardform.view.CardForm;

/**
 * Activities that contain this fragment must implement the
 * {@link PaymentFragmentDialog.OnRentPaymentListener} interface
 * to handle interaction events.
 * Use the {@link PaymentFragmentDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentFragmentDialog extends DialogFragment {
    private static final String ARG_PLACE_NAME = "placeName";

    private TextView mTvPlaceName;
    private CardForm mCardForm;
    private Button mBtnRent;

    private String mPlaceName;

    private OnRentPaymentListener mOnRentPaymentListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param placeName Name of selected Place
     * @return A new instance of fragment PaymentFragmentDialog.
     */
    public static PaymentFragmentDialog newInstance(String placeName) {
        PaymentFragmentDialog fragment = new PaymentFragmentDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PLACE_NAME, placeName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPlaceName = getArguments().getString(ARG_PLACE_NAME);
        }

        //Set Style of PaymentFragmentDialog
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        mTvPlaceName = (TextView) view.findViewById(R.id.tvPlaceName);
        mCardForm = (CardForm) view.findViewById(R.id.card_form);
        mBtnRent = (Button) view.findViewById(R.id.btnRent);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialize CardForm
        if (mCardForm != null) {
            mCardForm.cardRequired(true)
                    .expirationRequired(true)
                    .postalCodeRequired(true)
                    .cvvRequired(true)
                    .setup(getActivity());

            mCardForm.setPostalCodeIcon(0);
            mCardForm.getPostalCodeEditText().setFieldHint(getString(R.string.prompt_name));
        }

        //Set Place Name Text if exists
        if (mTvPlaceName != null && mPlaceName != null && !mPlaceName.isEmpty())
            mTvPlaceName.setText(mPlaceName);

        //Set Rent Payment Click Listener
        if (mBtnRent != null) {
            mBtnRent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Send onRentPaymentButtonClicked to activity if CardForm is valid
                    if (mCardForm != null && mCardForm.isValid()) {
                        if (mOnRentPaymentListener != null && mCardForm.getExpirationYear() != null && mCardForm.getExpirationYear().length() >= 2) {
                            mOnRentPaymentListener.onRentPaymentButtonClicked(mCardForm.getCardNumber(), mCardForm.getPostalCode(), mCardForm.getExpirationMonth() + "/" + mCardForm.getExpirationYear().substring(2), mCardForm.getCvv());
                            dismiss();
                        }
                    } else {
                        //Show message if CardForm is not valid
                        Toast.makeText(getActivity(), getString(R.string.error_payment_form), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRentPaymentListener) {
            mOnRentPaymentListener = (OnRentPaymentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnRentPaymentListener = null;
    }

    /**
     * The Fragment communicates with the Activity on rent payment
     *
     * @see com.aydozkan.crossover.gui.activity.PlacesActivity
     */
    public interface OnRentPaymentListener {
        void onRentPaymentButtonClicked(String cardNumber, String cardOwnerName, String expirationDate, String securityCode);
    }
}
