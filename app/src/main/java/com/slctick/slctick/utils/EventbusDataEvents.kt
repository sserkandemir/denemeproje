package com.slctick.slctick.utils

import com.slctick.slctick.Models.Users

class EventbusDataEvents{

    internal class KayitBilgileriniGonder(var telNo:String?, var email:String?, var verificationID:String?, var code:String?, var emailkayit:Boolean)

    internal class KullaniciBilgileriniGonder(var kullanici: Users?)

    internal class PaylasilacakResmiGonder(var resimYolu:String?)

    internal class GalerySecilenDosyaYolunuGonder(var dosyaYolu:String?)






}


