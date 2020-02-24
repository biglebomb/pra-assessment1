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
import org.d3if4090.praassessment1.databinding.FragmentPersegiBinding
import org.d3if4090.praassessment1.databinding.FragmentSegitigaBinding
private var alas = 0
private var tinggi = 0
private var luas: Double = 0.0
private var keliling = 0
/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSegitigaBinding>(
            inflater, R.layout.fragment_segitiga, container, false
        )

        if(savedInstanceState != null){
            luas = savedInstanceState.getDouble("luas")
            keliling = savedInstanceState.getInt("keliling")
            setText(binding)
        }

        binding.btHitung.setOnClickListener { v ->
            alas = binding.etAlas.text.toString().toInt()
            tinggi = binding.etTinggi.text.toString().toInt()

            if(alas == 0 && tinggi == 0){
                Toast.makeText(context, "Masukkan nilai lebih dari 0", Toast.LENGTH_SHORT).show()
            } else {
                luas = 0.5 * alas.toDouble() * tinggi.toDouble()
                keliling = alas + tinggi + pitagoras(alas, tinggi)

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
        outState.putDouble("luas", luas)
        outState.putInt("keliling", keliling)
    }

    private fun pitagoras(a: Int, b: Int): Int{
        return (a*a)+(b*b)
    }

    private fun setText(binding: FragmentSegitigaBinding) {
        binding.tvLuas.text = "Luas = ${luas}"
        binding.tvKeliling.text = "Keliling = ${keliling}"
    }

    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(activity as MainActivity)
            .setText("Luas Segitiga: " + luas + "\nKeliling Segitiga: " + keliling)
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
