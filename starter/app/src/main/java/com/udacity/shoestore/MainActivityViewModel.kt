package com.udacity.shoestore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.shoestore.models.Shoe
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(){

     private var _shoes = mutableListOf<Shoe>()
     private var _shoesList =  MutableLiveData<List<Shoe>>(_shoes)
     val shoesList: LiveData<List<Shoe>>
         get() = _shoesList


     val _cancelClicked = MutableLiveData<Boolean>()
    val cancelClicked :LiveData<Boolean>
        get() = _cancelClicked

     val _saveClicked = MutableLiveData<Boolean>()
     val saveClicked :LiveData<Boolean>
         get() = _saveClicked



    init{
        _cancelClicked.value = false
        _saveClicked.value = false

    }

     fun add(shoe: Shoe) = viewModelScope.launch {
         _shoes.add(shoe)

         //notify observer
         _shoesList.postValue(_shoes)
     }

    fun newShoe(name:String, company:String, size:String, description:String){
        val sizeDouble: Double = size.toDouble() // change size form string to double
        val s = Shoe(name,sizeDouble,company, description)
        onSaveClick() // sets it to true
        add(s)
    }

    fun onCancelClick(){
        _cancelClicked.value = true
    }

    fun afterCancelClick(){
        _cancelClicked.value = false
    }

     fun onSaveClick(){
         _saveClicked.value = true
     }

     fun afterSaveClick(){
         _saveClicked.value = false
     }


}