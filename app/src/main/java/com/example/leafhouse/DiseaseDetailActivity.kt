package com.example.leafhouse

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DiseaseDetailActivity : AppCompatActivity() {
    
    private lateinit var tvDiseaseName: TextView
    private lateinit var tvConfidence: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvSymptoms: TextView
    private lateinit var tvCauses: TextView
    private lateinit var tvTreatment: TextView
    private lateinit var tvPrevention: TextView
    private lateinit var btnBack: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disease_detail)
        
        // Initialize views
        tvDiseaseName = findViewById(R.id.tv_disease_name)
        tvConfidence = findViewById(R.id.tv_confidence)
        tvDescription = findViewById(R.id.tv_description)
        tvSymptoms = findViewById(R.id.tv_symptoms)
        tvCauses = findViewById(R.id.tv_causes)
        tvTreatment = findViewById(R.id.tv_treatment)
        tvPrevention = findViewById(R.id.tv_prevention)
        btnBack = findViewById(R.id.btn_back)
        
        // Get data from intent
        val diseaseName = intent.getStringExtra("DISEASE_NAME") ?: "Unknown"
        val confidence = intent.getFloatExtra("CONFIDENCE", 0f)
        
        // Get disease info from database
        val diseaseInfo = DiseaseDatabase.getDiseaseInfo(diseaseName)
        
        // Set data to views
        tvDiseaseName.text = diseaseInfo.name
        tvConfidence.text = "Độ chính xác: ${(confidence * 100).toInt()}%"
        tvDescription.text = diseaseInfo.description
        tvSymptoms.text = diseaseInfo.symptoms
        tvCauses.text = diseaseInfo.causes
        tvTreatment.text = diseaseInfo.treatment
        tvPrevention.text = diseaseInfo.prevention
        
        // Setup back button
        btnBack.setOnClickListener {
            finish()
        }
        
        // Enable up button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Chi tiết bệnh"
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
