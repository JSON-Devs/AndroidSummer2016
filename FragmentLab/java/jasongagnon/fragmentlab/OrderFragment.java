package jasongagnon.fragmentlab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    private EditText FName, LName, NoOfBars;
    private RadioButton ShippingExp, ShippingNor;
    private Spinner Type;
    private TextView Feedback;
    //private ArrayList<Order> candyOrders = new ArrayList<Order>();
    private List<String> candyList = new ArrayList<String>();
    private ArrayAdapter<String> dataAdapter;
    private Button saveOrder;
    View view;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        view = inflater.inflate(R.layout.fragment_order, container, false);

        FName = (EditText)view.findViewById(R.id.txtFName);
        LName = (EditText)view.findViewById(R.id.txtLName);
        NoOfBars = (EditText)view.findViewById(R.id.txtNoOfBars);

        Feedback = (TextView)view.findViewById(R.id.txtFeedback);

        ShippingExp = (RadioButton)view.findViewById(R.id.rbEx);
        ShippingNor = (RadioButton)view.findViewById(R.id.rbN);

        Type = (Spinner)view.findViewById(R.id.spinner);

        saveOrder = (Button)view.findViewById(R.id.btnSave);
        saveOrder.setOnClickListener(SaveButtonClickListener);

        //List<String> candyList = new ArrayList<String>();
        candyList.add("Milk Chocolate");
        candyList.add("Dark Chocolate");
        candyList.add("White Chocolate");


        dataAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,candyList);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        Type.setAdapter(dataAdapter);


        return view;
    }

    private View.OnClickListener SaveButtonClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            String fName, lName, type;
            int noOfBars;
            boolean isExp;

            fName = String.valueOf(FName.getText().toString());
            lName = String.valueOf(LName.getText().toString());
            noOfBars = Integer.valueOf(NoOfBars.getText().toString()).intValue();
            type = Type.getSelectedItem().toString();

            if (ShippingExp.isChecked()){
                isExp = true;
            }
            else {
                isExp = false;
            }

            Order candy = new Order();
            candy.setfName(fName);
            candy.setlName(lName);
            candy.setNoOfBars(noOfBars);
            candy.setType(type);
            candy.setShippingExpedited(isExp);

            MainActivity.CandyOrders.add(candy);

            onButtonPressed(candy);

        }
    };

    public void LoadFragmentData(Order order){

        FName.setText(order.getfName());
        LName.setText(order.getlName());
        NoOfBars.setText(String.valueOf(order.getNoOfBars()));
        int index = dataAdapter.getPosition(order.getType());
        Type.setSelection(index);
        if(order.isShippingExpedited()){
            ShippingExp.setChecked(true);
        }
        else{
            ShippingNor.setChecked(true);
        }
    }
    public void LoadListAgain(Order order){
        dataAdapter = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_spinner_item,candyList);

        dataAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);
        Type.setAdapter(dataAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Order uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        void onFragmentInteraction(Order uri);
    }
}
