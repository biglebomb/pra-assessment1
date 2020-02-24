package org.d3if4090.praassessment1


import android.content.ActivityNotFoundException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import org.d3if4090.praassessment1.databinding.FragmentMainBinding
import org.d3if4090.praassessment1.databinding.FragmentPersegiBinding

private var panjang = 0
private var lebar = 0
private var luas = 0
private var keliling = 0

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPersegiBinding>(
            inflater, R.layout.fragment_persegi, container, false
        )

        if(savedInstanceState != null){
            luas = savedInstanceState.getInt("luas")
            keliling = savedInstanceState.getInt("keliling")
            setText(binding)
        }

        binding.btHitung.setOnClickListener { v ->
            panjang = binding.etPanjang.text.toString().toInt()
            lebar = binding.etLebar.text.toString().toInt()

            if(panjang == 0 && lebar == 0){
                Toast.makeText(context, "Masukkan nilai lebih dari 0", Toast.LENGTH_SHORT).show()
            } else {
                luas = panjang * lebar
                keliling = 2 * (panjang + lebar)

                setText(binding)
            }
        }

        binding.btShare.setOnClickListener{
            onShare()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("luas", luas)
        outState.putInt("keliling", keliling)
    }

    private fun setText(binding: FragmentPersegiBinding) {
        binding.tvLuas.text = "Luas = ${luas}"
        binding.tvKeliling.text = "Keliling = ${keliling}"
    }

    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(activity as MainActivity)
            .setText("Luas Persegi: " + luas + "\nKeliling Persegi: " + keliling)
            .setType("text/plain")
            .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, "Tidak dapat mengshare hasil hitungan",
                Toast.LENGTH_LONG).show()
        }
    }
}
