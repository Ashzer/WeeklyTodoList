package com.example.mytodolist.features.ui.home;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mytodolist.R;
import com.example.mytodolist.core.calendar.utils.LocalDateUtils;
import com.example.mytodolist.databinding.DialogAddTodoBinding;

import java.time.LocalDate;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddDialog extends DialogFragment {
    DialogAddTodoBinding binding;
    DialogFragmentSaveTodoListener dialogFragmentSaveTodoListener;
    DialogFragmentCancelTodoListener dialogFragmentCancelTodoListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);

        Log.d(this.getClass().toString(), this.getTag());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogAddTodoBinding.inflate(inflater, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return binding.getRoot();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 구현부
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
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


        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                binding.dialogAddDeadlineDateTv.setText(LocalDateUtils.getDateFullName(LocalDate.of(i, i1 + 1, i2)));
            }
        }, LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth());
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        if (Objects.equals(this.getTag(), new Const().UPDATE_DIALOG)) {
            Bundle mArgs = getArguments();
            Todo oldTodo = Objects.requireNonNull(mArgs).getParcelable("todo");
            binding.dialogAddTodoTitleEt.setText(oldTodo.getTitle());
            binding.dialogAddTodoContentEt.setText(oldTodo.getContent());
            binding.dialogAddDeadlineDateTv.setText(LocalDateUtils.getDateFullName(oldTodo.getDeadline()));

            LocalDate date = oldTodo.getDeadline();
            datePickerDialog.updateDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());

            //저장버튼 리스너
            updateOldTodo(oldTodo);

            //원래 선택한 색상 확인하고 세팅
            setOldBackgroundColor(oldTodo.getColor());

            //수정 도중 취소
            binding.dialogAddCancelBtn.setOnClickListener(v -> {
                dialogFragmentCancelTodoListener.onCancel(oldTodo.getId());
                dismiss();
            });

        } else {
            //새 todo

            //데드라인 초기화
            binding.dialogAddDeadlineDateTv.setText(LocalDateUtils.getDateFullName(LocalDate.now()));
            //라디오 버튼 선택 초기화
            binding.dialogAddColorSelectionRg.check(R.id.dialogAddColor1Rb);

            //저장 버튼 리스너
            saveNewTodo();

            //취소 버튼 리스너
            binding.dialogAddCancelBtn.setOnClickListener(v -> dismiss());
        }

        //라디오 버튼 선택에 따라서 배경색 전환
        setTodoBackgroundColorListener();

        //현재 날짜 기준으로 Date Picker 화면에 띄움
        showDatePickerListener(datePickerDialog);
    }
    //원래 선택한 색상 확인하고 세팅
    void setOldBackgroundColor(int oldColor) {
        for (int childAt = 0; childAt < binding.dialogAddColorSelectionRg.getChildCount(); childAt++) {
            int childAtColor = binding.dialogAddColorSelectionRg.getChildAt(childAt).getBackgroundTintList().getDefaultColor();
            if (childAtColor == oldColor) {
                RadioGroup rg = binding.dialogAddColorSelectionRg;
                rg.check(rg.getChildAt(childAt).getId());
                binding.dialogAddItemClo.setBackgroundTintList(rg.getChildAt(childAt).getBackgroundTintList());
                Log.d(this.getClass().toString(), childAt + " should've selected.");
                break;
            }
        }
    }
    //라디오 버튼 선택에 따라서 배경색 전환
    void setTodoBackgroundColorListener() {
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

    //저장버튼 리스너
    void updateOldTodo(Todo oldTodo) {
        binding.dialogAddSaveBtn.setOnClickListener(v -> {
            LocalDate newDeadline = LocalDateUtils.getDateFromFullName(binding.dialogAddDeadlineDateTv.getText().toString());
            dialogFragmentSaveTodoListener.onSaved(
                    new Todo(
                            oldTodo.getId(),
                            binding.dialogAddItemClo.getBackgroundTintList().getDefaultColor(),
                            binding.dialogAddTodoTitleEt.getText().toString(),
                            oldTodo.getStart(),
                            newDeadline,
                            binding.dialogAddTodoContentEt.getText().toString()
                    )
            );
            dismiss();
        });
    }
    //저장 버튼 리스너
    void saveNewTodo() {
        binding.dialogAddSaveBtn.setOnClickListener(v -> {
            LocalDate newDeadline = LocalDateUtils.getDateFromFullName(binding.dialogAddDeadlineDateTv.getText().toString());
            dialogFragmentSaveTodoListener.onSaved(
                    new Todo(
                            0,
                            binding.dialogAddItemClo.getBackgroundTintList().getDefaultColor(),
                            binding.dialogAddTodoTitleEt.getText().toString(),
                            LocalDate.now(),
                            newDeadline,
                            binding.dialogAddTodoContentEt.getText().toString()
                    )
            );
            dismiss();
        });
    }
    //현재 날짜 기준으로 Date Picker 화면에 띄움
    void showDatePickerListener(DatePickerDialog datePickerDialog) {
        binding.dialogAddDatePickClo.setOnClickListener(v -> {
            LocalDate date = LocalDateUtils.getDateFromFullName(binding.dialogAddDeadlineDateTv.getText().toString());
            datePickerDialog.updateDate(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
            datePickerDialog.show();
        });
    }

    public void saveTodo(DialogFragmentSaveTodoListener listener) {
        dialogFragmentSaveTodoListener = listener;
    }

    public void cancelTodo(DialogFragmentCancelTodoListener listener) {
        dialogFragmentCancelTodoListener = listener;
    }

    //
    public interface DialogFragmentSaveTodoListener {
        void onSaved(Todo todo);
    }

    //수정 중 취소하면 수정하던 todo의 포지션 반환. 이후 동작은 Fragment에 정의되어 있음
    public interface DialogFragmentCancelTodoListener {
        void onCancel(long position);
    }
    //////////////////////////////////////////////////////////////////////////
}
