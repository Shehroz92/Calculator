package eu.practice.xmlapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast



class MainActivity : AppCompatActivity() {

     private var tvInput:TextView?=null
     private var lastDot:Boolean=false
     private var lastNumeric:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        tvInput=findViewById(R.id.tvInput)


    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastDot=false
        lastNumeric=true

    }
    fun onDigitClear(view: View){
        tvInput?.text=""

    }

    fun clickDot(view: View){
        if (lastNumeric && !lastDot){
            tvInput?.append(".")
            lastDot=true
            lastNumeric=false
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperateAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastDot=false
                lastNumeric=false

            }
        }
    }

    fun onEqual(view: View){
          if (lastNumeric){
              var tvValue=tvInput?.text.toString()
              var prefix=""
              try {
                  if (tvValue.startsWith("-"))
                  {
                      prefix="-"
                      tvValue=tvValue.substring(1)
                  }
                 if (tvValue.contains("-")){
                     val splitValue = tvValue.split("-")
                     var one =splitValue[0]
                     val two =splitValue[1]

                     if (prefix.isNotEmpty()){
                         one = prefix+ one
                     }
                     var result= one.toDouble() - two.toDouble()
                     tvInput?.text= removeZero(result.toString())
                     Toast.makeText(this,"@buildByAli",Toast.LENGTH_LONG).show()
                 }else if (tvValue.contains("+")){
                     val splitValue = tvValue.split("+")
                     var one =splitValue[0]
                     val two =splitValue[1]

                     if (prefix.isNotEmpty()){
                         one = prefix+ one
                     }
                     var result= one.toDouble() + two.toDouble()
                     tvInput?.text= removeZero(result.toString())
                     Toast.makeText(this,"@buildByAli",Toast.LENGTH_LONG).show()
                 } else if (tvValue.contains("/")){
                     val splitValue = tvValue.split("/")
                     var one =splitValue[0]
                     val two =splitValue[1]

                     if (prefix.isNotEmpty()){
                         one = prefix+ one
                     }
                     var result= one.toDouble() / two.toDouble()
                     tvInput?.text= removeZero(result.toString())
                     Toast.makeText(this,"@buildByAli",Toast.LENGTH_LONG).show()
                 } else if (tvValue.contains("*")){
                     val splitValue = tvValue.split("*")
                     var one =splitValue[0]
                     val two =splitValue[1]

                     if (prefix.isNotEmpty()){
                         one = prefix+ one
                     }
                     var result= one.toDouble() * two.toDouble()
                     tvInput?.text= removeZero(result.toString())
                     Toast.makeText(this,"@buildByAli",Toast.LENGTH_LONG).show()
                 }

              } catch (e:ArithmeticException){
                  e.printStackTrace()
              }
          }

    }
  private fun removeZero(result:String) :String{
      var value=result
      if (result.contains(".0")){
       value=result.substring(0,result.length-2)
      }
      return value
  }

  private fun isOperateAdded(value:String):Boolean{
     return if (value.startsWith("-")){
         false
     }else{
                 value.contains("/") ||
                 value.contains("+") ||
                 value.contains("-") ||
                 value.contains("*")


     }
  }



}
