package financialStudio.young_accountant;

import android.content.res.Resources;

import java.util.ArrayList;

public class ChartOFaccounts {

    private ArrayList<String> active_1;
    private ArrayList<String> active_2;
    private ArrayList<String> passsive_1;
    private ArrayList<String> passsive_2;
    private ArrayList<String> transite;
    private ArrayList<String> zabalans;
    final Resources resources;


    public ChartOFaccounts(Resources resources) {
        this.resources = resources;
        Initialization_active_1();
        Initialization_active_2();
        Initialization_passive_1();
        Initialization_passive_2();
        Initialization_transite();
        Initialization_zabalanse();
    }

    private void Initialization_active_1(){
        active_1 = new ArrayList<>();

        Resources res = getResources();

        active_1.add(res.getString(R.string.active_0100));
        active_1.add(res.getString(R.string.active_0110));
        active_1.add(res.getString(R.string.active_0111));
        active_1.add(res.getString(R.string.active_0112));
        active_1.add(res.getString(R.string.active_0120));
        active_1.add(res.getString(R.string.active_0130));
        active_1.add(res.getString(R.string.active_0140));
        active_1.add(res.getString(R.string.active_0150));
        active_1.add(res.getString(R.string.active_0160));
        active_1.add(res.getString(R.string.active_0170));
        active_1.add(res.getString(R.string.active_0180));
        active_1.add(res.getString(R.string.active_0190));
        active_1.add(res.getString(R.string.active_0199));
        active_1.add(res.getString(R.string.active_0200));
        active_1.add(res.getString(R.string.active_0211));
        active_1.add(res.getString(R.string.active_0212));
        active_1.add(res.getString(R.string.active_0220));
        active_1.add(res.getString(R.string.active_0230));
        active_1.add(res.getString(R.string.active_0240));
        active_1.add(res.getString(R.string.active_0250));
        active_1.add(res.getString(R.string.active_0260));
        active_1.add(res.getString(R.string.active_0270));
        active_1.add(res.getString(R.string.active_0280));
        active_1.add(res.getString(R.string.active_0290));
        active_1.add(res.getString(R.string.active_0299));
        active_1.add(res.getString(R.string.active_0300));
        active_1.add(res.getString(R.string.active_0310));
        active_1.add(res.getString(R.string.active_0400));
        active_1.add(res.getString(R.string.active_0410));
        active_1.add(res.getString(R.string.active_0420));
        active_1.add(res.getString(R.string.active_0430));
        active_1.add(res.getString(R.string.active_0440));
        active_1.add(res.getString(R.string.active_0460));
        active_1.add(res.getString(R.string.active_0470));
        active_1.add(res.getString(R.string.active_0480));
        active_1.add(res.getString(R.string.active_0490));
        active_1.add(res.getString(R.string.active_0500));
        active_1.add(res.getString(R.string.active_0510));
        active_1.add(res.getString(R.string.active_0520));
        active_1.add(res.getString(R.string.active_0530));
        active_1.add(res.getString(R.string.active_0540));
        active_1.add(res.getString(R.string.active_0560));
        active_1.add(res.getString(R.string.active_0570));
        active_1.add(res.getString(R.string.active_0590));
        active_1.add(res.getString(R.string.active_0600));
        active_1.add(res.getString(R.string.active_0610));
        active_1.add(res.getString(R.string.active_0620));
        active_1.add(res.getString(R.string.active_0630));
        active_1.add(res.getString(R.string.active_0640));
        active_1.add(res.getString(R.string.active_0690));
        active_1.add(res.getString(R.string.active_0700));
        active_1.add(res.getString(R.string.active_0710));
        active_1.add(res.getString(R.string.active_0720));
        active_1.add(res.getString(R.string.active_0800));
        active_1.add(res.getString(R.string.active_0810));
        active_1.add(res.getString(R.string.active_0820));
        active_1.add(res.getString(R.string.active_0830));
        active_1.add(res.getString(R.string.active_0840));
        active_1.add(res.getString(R.string.active_0850));
        active_1.add(res.getString(R.string.active_0860));
        active_1.add(res.getString(R.string.active_0890));
        active_1.add(res.getString(R.string.active_0900));
        active_1.add(res.getString(R.string.active_0910));
        active_1.add(res.getString(R.string.active_0920));
        active_1.add(res.getString(R.string.active_0930));
        active_1.add(res.getString(R.string.active_0940));
        active_1.add(res.getString(R.string.active_0950));
        active_1.add(res.getString(R.string.active_0960));
        active_1.add(res.getString(R.string.active_0990));
    }

    private void Initialization_active_2(){
        active_2 = new ArrayList<>();

        Resources res = getResources();

        active_2.add(res.getString(R.string.active_1000));
        active_2.add(res.getString(R.string.active_1010));
        active_2.add(res.getString(R.string.active_1020));
        active_2.add(res.getString(R.string.active_1030));
        active_2.add(res.getString(R.string.active_1040));
        active_2.add(res.getString(R.string.active_1050));
        active_2.add(res.getString(R.string.active_1060));
        active_2.add(res.getString(R.string.active_1070));
        active_2.add(res.getString(R.string.active_1080));
        active_2.add(res.getString(R.string.active_1090));
        active_2.add(res.getString(R.string.active_1100));
        active_2.add(res.getString(R.string.active_1110));
        active_2.add(res.getString(R.string.active_1120));
        active_2.add(res.getString(R.string.active_1200));
        active_2.add(res.getString(R.string.active_1300));
        active_2.add(res.getString(R.string.active_1400));
        active_2.add(res.getString(R.string.active_1500));
        active_2.add(res.getString(R.string.active_1510));
        active_2.add(res.getString(R.string.active_1600));
        active_2.add(res.getString(R.string.active_1610));
        active_2.add(res.getString(R.string.active_1700));
        active_2.add(res.getString(R.string.active_1800));
        active_2.add(res.getString(R.string.active_1900));
        active_2.add(res.getString(R.string.active_2000));
        active_2.add(res.getString(R.string.active_2010));
        active_2.add(res.getString(R.string.active_2100));
        active_2.add(res.getString(R.string.active_2110));
        active_2.add(res.getString(R.string.active_2200));
        active_2.add(res.getString(R.string.active_2300));
        active_2.add(res.getString(R.string.active_2310));
        active_2.add(res.getString(R.string.active_2310));
        active_2.add(res.getString(R.string.active_2400));
        active_2.add(res.getString(R.string.active_2310));
        active_2.add(res.getString(R.string.active_2400));
        active_2.add(res.getString(R.string.active_2500));
        active_2.add(res.getString(R.string.active_2510));
        active_2.add(res.getString(R.string.active_2600));
        active_2.add(res.getString(R.string.active_2610));
        active_2.add(res.getString(R.string.active_2700));
        active_2.add(res.getString(R.string.active_2710));
        active_2.add(res.getString(R.string.active_2800));
        active_2.add(res.getString(R.string.active_2810));
        active_2.add(res.getString(R.string.active_2820));
        active_2.add(res.getString(R.string.active_2830));
        active_2.add(res.getString(R.string.active_2900));
        active_2.add(res.getString(R.string.active_2910));
        active_2.add(res.getString(R.string.active_2920));
        active_2.add(res.getString(R.string.active_2930));
        active_2.add(res.getString(R.string.active_2940));
        active_2.add(res.getString(R.string.active_2950));
        active_2.add(res.getString(R.string.active_2960));
        active_2.add(res.getString(R.string.active_2970));
        active_2.add(res.getString(R.string.active_2980));
        active_2.add(res.getString(R.string.active_2990));
        active_2.add(res.getString(R.string.active_3000));
        active_2.add(res.getString(R.string.active_3100));
        active_2.add(res.getString(R.string.active_3110));
        active_2.add(res.getString(R.string.active_3120));
        active_2.add(res.getString(R.string.active_3190));
        active_2.add(res.getString(R.string.active_3200));
        active_2.add(res.getString(R.string.active_3210));
        active_2.add(res.getString(R.string.active_3220));
        active_2.add(res.getString(R.string.active_3290));
        active_2.add(res.getString(R.string.active_3300));
        active_2.add(res.getString(R.string.active_3400));
        active_2.add(res.getString(R.string.active_3500));
        active_2.add(res.getString(R.string.active_3600));
        active_2.add(res.getString(R.string.active_3700));
        active_2.add(res.getString(R.string.active_3800));
        active_2.add(res.getString(R.string.active_3900));
        active_2.add(res.getString(R.string.active_4000));
        active_2.add(res.getString(R.string.active_4010));
        active_2.add(res.getString(R.string.active_4020));
        active_2.add(res.getString(R.string.active_4100));
        active_2.add(res.getString(R.string.active_4110));
        active_2.add(res.getString(R.string.active_4120));
        active_2.add(res.getString(R.string.active_4200));
        active_2.add(res.getString(R.string.active_4210));
        active_2.add(res.getString(R.string.active_4220));
        active_2.add(res.getString(R.string.active_4230));
        active_2.add(res.getString(R.string.active_4290));
        active_2.add(res.getString(R.string.active_4300));
        active_2.add(res.getString(R.string.active_4310));
        active_2.add(res.getString(R.string.active_4320));
        active_2.add(res.getString(R.string.active_4330));
        active_2.add(res.getString(R.string.active_4400));
        active_2.add(res.getString(R.string.active_4410));
        active_2.add(res.getString(R.string.active_4500));
        active_2.add(res.getString(R.string.active_4510));
        active_2.add(res.getString(R.string.active_4600));
        active_2.add(res.getString(R.string.active_4610));
        active_2.add(res.getString(R.string.active_4700));
        active_2.add(res.getString(R.string.active_4710));
        active_2.add(res.getString(R.string.active_4720));
        active_2.add(res.getString(R.string.active_4730));
        active_2.add(res.getString(R.string.active_4790));
        active_2.add(res.getString(R.string.active_4800));
        active_2.add(res.getString(R.string.active_4810));
        active_2.add(res.getString(R.string.active_4820));
        active_2.add(res.getString(R.string.active_4830));
        active_2.add(res.getString(R.string.active_4840));
        active_2.add(res.getString(R.string.active_4850));
        active_2.add(res.getString(R.string.active_4860));
        active_2.add(res.getString(R.string.active_4890));
        active_2.add(res.getString(R.string.active_4900));
        active_2.add(res.getString(R.string.active_4910));
        active_2.add(res.getString(R.string.active_5000));
        active_2.add(res.getString(R.string.active_5010));
        active_2.add(res.getString(R.string.active_5020));
        active_2.add(res.getString(R.string.active_5100));
        active_2.add(res.getString(R.string.active_5110));
        active_2.add(res.getString(R.string.active_5200));
        active_2.add(res.getString(R.string.active_5210));
        active_2.add(res.getString(R.string.active_5220));
        active_2.add(res.getString(R.string.active_5300));
        active_2.add(res.getString(R.string.active_5400));
        active_2.add(res.getString(R.string.active_5500));
        active_2.add(res.getString(R.string.active_5510));
        active_2.add(res.getString(R.string.active_5520));
        active_2.add(res.getString(R.string.active_5530));
        active_2.add(res.getString(R.string.active_5600));
        active_2.add(res.getString(R.string.active_5610));
        active_2.add(res.getString(R.string.active_5700));
        active_2.add(res.getString(R.string.active_5710));
        active_2.add(res.getString(R.string.active_5800));
        active_2.add(res.getString(R.string.active_5810));
        active_2.add(res.getString(R.string.active_5830));
        active_2.add(res.getString(R.string.active_5890));
        active_2.add(res.getString(R.string.active_5900));
        active_2.add(res.getString(R.string.active_5910));
        active_2.add(res.getString(R.string.active_5920));
    }
    private void Initialization_passive_1(){
        passsive_1 = new ArrayList<>();

        Resources res = getResources();

        passsive_1.add(res.getString(R.string.passive_6000));
        passsive_1.add(res.getString(R.string.passive_6010));
        passsive_1.add(res.getString(R.string.passive_6020));
        passsive_1.add(res.getString(R.string.passive_6100));
        passsive_1.add(res.getString(R.string.passive_6110));
        passsive_1.add(res.getString(R.string.passive_6120));
        passsive_1.add(res.getString(R.string.passive_6200));
        passsive_1.add(res.getString(R.string.passive_6210));
        passsive_1.add(res.getString(R.string.passive_6220));
        passsive_1.add(res.getString(R.string.passive_6230));
        passsive_1.add(res.getString(R.string.passive_6240));
        passsive_1.add(res.getString(R.string.passive_6250));
        passsive_1.add(res.getString(R.string.passive_6290));
        passsive_1.add(res.getString(R.string.passive_6300));
        passsive_1.add(res.getString(R.string.passive_6310));
        passsive_1.add(res.getString(R.string.passive_6320));
        passsive_1.add(res.getString(R.string.passive_6390));
        passsive_1.add(res.getString(R.string.passive_6400));
        passsive_1.add(res.getString(R.string.passive_6410));
        passsive_1.add(res.getString(R.string.passive_6500));
        passsive_1.add(res.getString(R.string.passive_6510));
        passsive_1.add(res.getString(R.string.passive_6520));
        passsive_1.add(res.getString(R.string.passive_6600));
        passsive_1.add(res.getString(R.string.passive_6610));
        passsive_1.add(res.getString(R.string.passive_6620));
        passsive_1.add(res.getString(R.string.passive_6630));
        passsive_1.add(res.getString(R.string.passive_6700));
        passsive_1.add(res.getString(R.string.passive_6710));
        passsive_1.add(res.getString(R.string.passive_6720));
        passsive_1.add(res.getString(R.string.passive_6800));
        passsive_1.add(res.getString(R.string.passive_6810));
        passsive_1.add(res.getString(R.string.passive_6820));
        passsive_1.add(res.getString(R.string.passive_6830));
        passsive_1.add(res.getString(R.string.passive_6840));
        passsive_1.add(res.getString(R.string.passive_6900));
        passsive_1.add(res.getString(R.string.passive_6910));
        passsive_1.add(res.getString(R.string.passive_6920));
        passsive_1.add(res.getString(R.string.passive_6930));
        passsive_1.add(res.getString(R.string.passive_6930));
        passsive_1.add(res.getString(R.string.passive_6940));
        passsive_1.add(res.getString(R.string.passive_6950));
        passsive_1.add(res.getString(R.string.passive_6960));
        passsive_1.add(res.getString(R.string.passive_6970));
        passsive_1.add(res.getString(R.string.passive_6990));
        passsive_1.add(res.getString(R.string.passive_7000));
        passsive_1.add(res.getString(R.string.passive_7010));
        passsive_1.add(res.getString(R.string.passive_7020));
        passsive_1.add(res.getString(R.string.passive_7100));
        passsive_1.add(res.getString(R.string.passive_7110));
        passsive_1.add(res.getString(R.string.passive_7120));
        passsive_1.add(res.getString(R.string.passive_7200));
        passsive_1.add(res.getString(R.string.passive_7210));
        passsive_1.add(res.getString(R.string.passive_7220));
        passsive_1.add(res.getString(R.string.passive_7230));
        passsive_1.add(res.getString(R.string.passive_7240));
        passsive_1.add(res.getString(R.string.passive_7250));
        passsive_1.add(res.getString(R.string.passive_7290));
        passsive_1.add(res.getString(R.string.passive_7300));
        passsive_1.add(res.getString(R.string.passive_7310));
        passsive_1.add(res.getString(R.string.passive_7400));
        passsive_1.add(res.getString(R.string.passive_7500));
        passsive_1.add(res.getString(R.string.passive_7600));
        passsive_1.add(res.getString(R.string.passive_7700));
        passsive_1.add(res.getString(R.string.passive_7800));
        passsive_1.add(res.getString(R.string.passive_7810));
        passsive_1.add(res.getString(R.string.passive_7820));
        passsive_1.add(res.getString(R.string.passive_7830));
        passsive_1.add(res.getString(R.string.passive_7840));
        passsive_1.add(res.getString(R.string.passive_7900));
        passsive_1.add(res.getString(R.string.passive_7910));
        passsive_1.add(res.getString(R.string.passive_7920));
    }

    private void Initialization_passive_2(){
        passsive_2 = new ArrayList<>();

        Resources res = getResources();

        passsive_2.add(res.getString(R.string.passive_8000));
        passsive_2.add(res.getString(R.string.passive_8100));
        passsive_2.add(res.getString(R.string.passive_8200));
        passsive_2.add(res.getString(R.string.passive_8300));
        passsive_2.add(res.getString(R.string.passive_8310));
        passsive_2.add(res.getString(R.string.passive_8320));
        passsive_2.add(res.getString(R.string.passive_8330));
        passsive_2.add(res.getString(R.string.passive_8400));
        passsive_2.add(res.getString(R.string.passive_8410));
        passsive_2.add(res.getString(R.string.passive_8420));
        passsive_2.add(res.getString(R.string.passive_8500));
        passsive_2.add(res.getString(R.string.passive_8510));
        passsive_2.add(res.getString(R.string.passive_8520));
        passsive_2.add(res.getString(R.string.passive_8530));
        passsive_2.add(res.getString(R.string.passive_8600));
        passsive_2.add(res.getString(R.string.passive_8610));
        passsive_2.add(res.getString(R.string.passive_8620));
        passsive_2.add(res.getString(R.string.passive_8700));
        passsive_2.add(res.getString(R.string.passive_8710));
        passsive_2.add(res.getString(R.string.passive_8720));
        passsive_2.add(res.getString(R.string.passive_8800));
        passsive_2.add(res.getString(R.string.passive_8810));
        passsive_2.add(res.getString(R.string.passive_8820));
        passsive_2.add(res.getString(R.string.passive_8830));
        passsive_2.add(res.getString(R.string.passive_8840));
        passsive_2.add(res.getString(R.string.passive_8890));
    }

    private void Initialization_transite(){
        transite = new ArrayList<>();

        Resources res = getResources();

        transite.add(res.getString(R.string.transit_9000));
        transite.add(res.getString(R.string.transit_9010));
        transite.add(res.getString(R.string.transit_9020));
        transite.add(res.getString(R.string.transit_9030));
        transite.add(res.getString(R.string.transit_9040));
        transite.add(res.getString(R.string.transit_9050));
        transite.add(res.getString(R.string.transit_9100));
        transite.add(res.getString(R.string.transit_9110));
        transite.add(res.getString(R.string.transit_9120));
        transite.add(res.getString(R.string.transit_9130));
        transite.add(res.getString(R.string.transit_9140));
        transite.add(res.getString(R.string.transit_9150));
        transite.add(res.getString(R.string.transit_9200));
        transite.add(res.getString(R.string.transit_9210));
        transite.add(res.getString(R.string.transit_9220));
        transite.add(res.getString(R.string.transit_9300));
        transite.add(res.getString(R.string.transit_9310));
        transite.add(res.getString(R.string.transit_9320));
        transite.add(res.getString(R.string.transit_9330));
        transite.add(res.getString(R.string.transit_9340));
        transite.add(res.getString(R.string.transit_9350));
        transite.add(res.getString(R.string.transit_9360));
        transite.add(res.getString(R.string.transit_9370));
        transite.add(res.getString(R.string.transit_9380));
        transite.add(res.getString(R.string.transit_9390));
        transite.add(res.getString(R.string.transit_9400));
        transite.add(res.getString(R.string.transit_9410));
        transite.add(res.getString(R.string.transit_9420));
        transite.add(res.getString(R.string.transit_9430));
        transite.add(res.getString(R.string.transit_9500));
        transite.add(res.getString(R.string.transit_9510));
        transite.add(res.getString(R.string.transit_9520));
        transite.add(res.getString(R.string.transit_9530));
        transite.add(res.getString(R.string.transit_9540));
        transite.add(res.getString(R.string.transit_9550));
        transite.add(res.getString(R.string.transit_9560));
        transite.add(res.getString(R.string.transit_9590));
        transite.add(res.getString(R.string.transit_9600));
        transite.add(res.getString(R.string.transit_9610));
        transite.add(res.getString(R.string.transit_9620));
        transite.add(res.getString(R.string.transit_9630));
        transite.add(res.getString(R.string.transit_9690));
        transite.add(res.getString(R.string.transit_9700));
        transite.add(res.getString(R.string.transit_9710));
        transite.add(res.getString(R.string.transit_9720));
        transite.add(res.getString(R.string.transit_9800));
        transite.add(res.getString(R.string.transit_9810));
        transite.add(res.getString(R.string.transit_9820));
        transite.add(res.getString(R.string.transit_9900));
        transite.add(res.getString(R.string.transit_9910));
    }

    private void Initialization_zabalanse(){
        zabalans = new ArrayList<>();

        Resources res = getResources();

        zabalans.add(res.getString(R.string.zabalans_001));
        zabalans.add(res.getString(R.string.zabalans_002));
        zabalans.add(res.getString(R.string.zabalans_003));
        zabalans.add(res.getString(R.string.zabalans_004));
        zabalans.add(res.getString(R.string.zabalans_005));
        zabalans.add(res.getString(R.string.zabalans_006));
        zabalans.add(res.getString(R.string.zabalans_007));
        zabalans.add(res.getString(R.string.zabalans_008));
        zabalans.add(res.getString(R.string.zabalans_009));
        zabalans.add(res.getString(R.string.zabalans_010));
        zabalans.add(res.getString(R.string.zabalans_011));
        zabalans.add(res.getString(R.string.zabalans_012));
        zabalans.add(res.getString(R.string.zabalans_013));
        zabalans.add(res.getString(R.string.zabalans_014));
        zabalans.add(res.getString(R.string.zabalans_015));
        zabalans.add(res.getString(R.string.zabalans_016));
    }

    public static ArrayList<String> search(String str, ArrayList<String> list){

        int count = list.size();

        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < count; i++){

            if (list.get(i).startsWith(str)){
                result.add(list.get(i));
            } else if ((count - 1 - i >= str.length()) && (list.get(i).startsWith(str, i))){
                result.add(list.get(i));
            }

        }

        return list;
    }

    public ArrayList<String> getActive_1() {
        return active_1;
    }

    public ArrayList<String> getActive_2() {
        return active_2;
    }

    public ArrayList<String> getPasssive_1() {
        return passsive_1;
    }

    public ArrayList<String> getPasssive_2() {
        return passsive_2;
    }

    public ArrayList<String> getTransite() {
        return transite;
    }

    public ArrayList<String> getZabalans() {
        return zabalans;
    }

    private Resources getResources() {
        return resources;
    }
}
