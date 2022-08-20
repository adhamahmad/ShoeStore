package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ShoeListBinding
import com.udacity.shoestore.databinding.ShoeRowBinding
import com.udacity.shoestore.models.Shoe
import kotlinx.android.synthetic.main.instruction.*


/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeListFragment : Fragment() {
lateinit var binding:ShoeListBinding
 var listIndex = 0;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = DataBindingUtil.inflate(inflater,R.layout.shoe_list,container,false)
        binding.buttonGoToDetails.setOnClickListener{view: View ->
            view.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
        }

        val viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
        binding.setLifecycleOwner (this)

        viewModel.shoesList.observe( viewLifecycleOwner , Observer { shoes ->
                shoes?.let{
                    displayShoes(it)
                }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun displayShoes(shoes: List<Shoe>) {
        for(i in listIndex until shoes.size){
            displayShoe(shoes[i])
            listIndex++;
        }

    }

    private fun displayShoe(shoe: Shoe) {
        val binding2:ShoeRowBinding = DataBindingUtil.inflate(layoutInflater,R.layout.shoe_row,null,false)
        binding2.shoeData = shoe
        binding.linearLayout.addView(binding2.root,0) // o so it gets added to to the top

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.logout,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,view!!.findNavController())
                || super.onOptionsItemSelected(item)

    }
}