package jasongagnon.finalexam;

import android.content.Context;
import android.graphics.Path;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.WindowDecorActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Top.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Top#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Top extends Fragment {

    Button Submit;
    EditText Amount;
    RadioButton Deposit, Withdrawal;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Top() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Top.
     */
    // TODO: Rename and change types and number of parameters
    public static Top newInstance(String param1, String param2) {
        Top fragment = new Top();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top, container, false);
        Submit = (Button)view.findViewById(R.id.btnSub);
        Submit.setOnClickListener(SubmitClickListener);
        Amount = (EditText)view.findViewById(R.id.etAmount);
        Deposit = (RadioButton)view.findViewById(R.id.rbDep);
        Withdrawal = (RadioButton)view.findViewById(R.id.rbW);
        return view;
    }

    private View.OnClickListener SubmitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String RequestedAmount = Amount.getText().toString();
            String Operation;
            if(Deposit.isChecked()) {
                Operation = Deposit.getText().toString();
            }
            else{
                Operation = Withdrawal.getText().toString();
            }
            onButtonPressed(RequestedAmount, Operation);
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String RequestedAmount, String Operation) {
        if (mListener != null) {
            mListener.onFragmentInteraction(RequestedAmount, Operation);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String RequestedAmount, String Operation);
    }
}
