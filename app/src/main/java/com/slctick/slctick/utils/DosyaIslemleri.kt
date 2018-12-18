package com.slctick.slctick.utils

import java.io.File
import java.util.*


class DosyaIslemleri {

    companion object {

        fun klasordekiDosyalariGetir(klasorAdi:String):ArrayList<String>{

            var tumDosyalar=ArrayList<String>()

            var file=File(klasorAdi)

            //parametre olarak gönderdiğimiz klasordeki tüm dosyalar alınız
            var klasordekiTumDosyalar=file.listFiles()

            //parametre olarak gönderdiğimiz klasor yolunda eleman olup olmadıgı kontrol edildi
            if(klasordekiTumDosyalar != null){

                //galeriden getirilen resimlerin tarihe göre sondan basa listelenmesi
                if(klasordekiTumDosyalar.size>1){

                    Arrays.sort(klasordekiTumDosyalar, object : Comparator<File>{
                        override fun compare(o1: File?, o2: File?): Int {

                            if(o1!!.lastModified() > o2!!.lastModified()){
                                return -1
                            }else return 1

                        }


                    })

                }

                for (i in 0..klasordekiTumDosyalar.size-1){

                    //sadece dosyalara bakılır
                    if(klasordekiTumDosyalar[i].isFile){

                        //Log.e("HATA","okunan veri bir dosya")

                        //okudugumuz dosyanın telefondaki yeri ve de adını içerir.
                        //files://root/logo.png
                        var okunanDosyaYolu=klasordekiTumDosyalar[i].absolutePath

                        //Log.e("HATA","okunan dosya yolu"+okunanDosyaYolu)

                        var noktaninSonIndexi= okunanDosyaYolu.lastIndexOf(".")
                        if(noktaninSonIndexi>0){
                            var dosyaTuru=okunanDosyaYolu.substring(noktaninSonIndexi)

                            //Log.e("HATA","okunan dosya türü"+dosyaTuru)

                            if(dosyaTuru!= null && (dosyaTuru.equals(".jpg") || dosyaTuru.equals(".jpeg") || dosyaTuru.equals(".png") || dosyaTuru.equals(".mp4"))){


                                tumDosyalar.add(okunanDosyaYolu)
                                //Log.e("HATA","arrayliste eklenen dosya"+okunanDosyaYolu)
                            }
                        }



                    }

                }

            }

            return tumDosyalar

        }


    }

}