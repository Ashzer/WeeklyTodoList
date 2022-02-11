package com.example.mytodolist.features.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytodolist.R;
import com.example.mytodolist.databinding.DialogAddTodoBinding;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddDialog extends DialogFragment {
    DialogAddTodoBinding binding;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);

        Log.d(this.getClass().toString(),this.getTag());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogAddTodoBinding.inflate(inflater, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()){
            @Override
            public void onBackPressed() {
                super.onBackPressed();
                binding.dialogAddCancelBtn.performClick();
            }
        };
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        binding.dialogAddTodoTitleEt.requestFocus();
        if(Objects.equals(this.getTag(), new Const().UPDATE_DIALOG)){
            Bundle mArgs = getArguments();
            Todo oldTodo = Objects.requireNonNull(mArgs).getParcelable("todo");
            binding.dialogAddTodoTitleEt.setText(oldTodo.getTitle());
            binding.dialogAddTodoContentEt.setText(oldTodo.getContent());

            binding.dialogAddSaveBtn.setOnClickListener(view1 -> {
                dialogFragmentSaveTodoListener.onSaved(
                        new Todo(
                                oldTodo.getId(),
                                binding.dialogAddItemClo.getBackgroundTintList().getDefaultColor(),
                                binding.dialogAddTodoTitleEt.getText().toString(),
                                oldTodo.getDate(),
                                binding.dialogAddTodoContentEt.getText().toString()
                        )
                );
                dismiss();
            });

            binding.dialogAddColorSelectionRg.check(R.id.dialogAddColor1Rb);
            for(int childAt = 0 ; childAt < binding.dialogAddColorSelectionRg.getChildCount() ; childAt++){
                int childAtColor = binding.dialogAddColorSelectionRg.getChildAt(childAt).getBackgroundTintList().getDefaultColor();
                if(childAtColor == oldTodo.getColor()){
                    RadioGroup rg = binding.dialogAddColorSelectionRg;
                    rg.check(rg.getChildAt(childAt).getId());
                    binding.dialogAddItemClo.setBackgroundTintList(rg.getChildAt(childAt).getBackgroundTintList());
                    Log.d(this.getClass().toString(), childAt+ " should've selected.");
                    break;
                }
            }

            binding.dialogAddCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogFragmentCancelTodoListener.onCancel(oldTodo.getId());
                    dismiss();
                }
            });

        }else{

            binding.dialogAddSaveBtn.setOnClickListener(view1 -> {
                dialogFragmentSaveTodoListener.onSaved(
                        new Todo(
                                0,
                                binding.dialogAddItemClo.getBackgroundTintList().getDefaultColor(),
                                binding.dialogAddTodoTitleEt.getText().toString(),
                                LocalDate.now(),
                                binding.dialogAddTodoContentEt.getText().toString()
                        )
                );
                dismiss();
            });
            binding.dialogAddColorSelectionRg.check(R.id.dialogAddColor1Rb);

            binding.dialogAddCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
        }

        binding.dialogAddColorSelectionRg.setOnCheckedChangeListener((radioGroup, i) -> {
            ColorStateList tintList;
            switch (i) {
                case R.id.dialogAddColor1Rb:
                default:
                    tintList = binding.dialogAddColor1Rb.getBackgroundTintList();
                    break;
                case R.id.dialogAddColor2Rb:
                    tintList = binding.dialogAddColor2Rb.getBackgroundTintList();
                    break;
                case R.id.dialogAddColor3Rb:
                    tintList = binding.dialogAddColor3Rb.getBackgroundTintList();
                    break;
                case R.id.dialogAddColor4Rb:
                    tintList = binding.dialogAddColor4Rb.getBackgroundTintList();
                    break;
                case R.id.dialogAddColor5Rb:
                    tintList = binding.dialogAddColor5Rb.getBackgroundTintList();
                    break;
            }
            binding.dialogAddItemClo.setBackgroundTintList(tintList);

        });


    }

    public interface DialogFragmentSaveTodoListener{
        void onSaved(Todo todo);
    }
    DialogFragmentSaveTodoListener dialogFragmentSaveTodoListener;

    public void saveTodo(DialogFragmentSaveTodoListener listener){
        dialogFragmentSaveTodoListener = listener;
    }

    public interface DialogFragmentCancelTodoListener{
        void onCancel(long position);
    }
    DialogFragmentCancelTodoListener dialogFragmentCancelTodoListener;

    public void cancelTodo(DialogFragmentCancelTodoListener listener){
        dialogFragmentCancelTodoListener = listener;
    }
}
