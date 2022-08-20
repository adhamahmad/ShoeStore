package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.databinding.ShoeDetailBinding
import kotlinx.android.synthetic.main.instruction.*

class ShoeDetailsFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ShoeDetailBinding = DataBindingUtil.inflate(inflater,R.layout.shoe_detail,container,false)

        val viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        binding.mainActivityViewModel = viewModel
        binding.setLifecycleOwner (this)

        viewModel.saveClicked.observe( viewLifecycleOwner, Observer { clicked ->
            if(clicked){
                // code that gets called anytime the livedata(new shoe is added) is changed
                viewModel.afterSaveClick() // return it back to false
                goToShoeList()
            }
        })



        viewModel.cancelClicked.observe( viewLifecycleOwner, Observer { clicked ->
            if(clicked){
                // code that gets called anytime the livedata(new shoe is added) is changed
                viewModel.afterCancelClick() // return it back to false
                goToShoeList()
            }
        })


        return  binding.root
    }
   private fun goToShoeList(){
        val action = ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }


}