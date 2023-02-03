package com.example.sample.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sample.dto.SweetsData;
import com.example.sample.entity.Sweets;
import com.example.sample.entity.SweetsForm;
import com.example.sample.repository.SweetsRepository;

@Service
public class SweetsService {
	
	@Autowired
	SweetsRepository repository;	

	/**
	 * ショーケースに陳列する
	 * mydb.sweetsのデータ全件を返却
	 * @return List<Sweets>
	 */
	public List<Sweets> getShowcaseData() { //getShowcaseDataメソッドの実行したらfindAll()でデータ全件を返す
		return repository.findAll();
	}

	/**
	 * ショップで表示する
	 * mydb.sweetsのデータを全件取得し、
	 * ショップ用のFormに移し替えて返却
	 * @return SweetsForm
	 */
	public SweetsForm getShopData() {
		List<Sweets> list = repository.findAll();
		// エンティティを画面データに詰めかえる
		List<SweetsData> sweetsList = new ArrayList<SweetsData>();
		for (Sweets sweets : list) {
			SweetsData data = new SweetsData();//クラス名 インスタンス名 = new コンストラクタ名([引数]);
			data.setId(sweets.getId());
			data.setItem(sweets.getItem());
			data.setStock(sweets.getStock());
			data.setPurchases(0);; // 購入数の初期値:0
			sweetsList.add(data); //SweetsDataのsweetsLisにdataを追加
		}
		SweetsForm sweetsForm = new SweetsForm();//SweetsFormをインスタンス化
		sweetsForm.setSweetsList(sweetsList);//setSweetsListにsweetsListをセットしてcontrollerのsweetsFormに返す
		return sweetsForm;
	}

	/**
	 * 買い物情報の更新をして
	 * お買い上げ商品のリストを返却
	 * @return List<Sweets>
	 */
	public List<SweetsData> updateBuy(SweetsForm sweetsForm) {//Postで渡されたデータを受け取ったsweetsForm
		// お買い上げリスト
		List<SweetsData> shoppingList = new ArrayList<SweetsData>();//shoppingLisのインスタンス化
		for (SweetsData sweets : sweetsForm.getSweetsList()) {//（型 変数名 : コレクション名)対象となる配列の要素の数だけ繰り返し（要素を一つ取りだし変数に格納）
			// 購入数が0以上の商品をお買い上げ
			if (sweets.getPurchases() > 0) {
				SweetsData data = new SweetsData();//SweetsDataを dataという名前でインスタンス化
				data.setId(sweets.getId());//受け取ったsweetsデータをdataに書き写す
				data.setItem(sweets.getItem());
				data.setPurchases(sweets.getPurchases());
				shoppingList.add(data);//SweetsDataのshoppingListにdataを追加

				// DB更新
				repository.updateStock(sweets.getPurchases(), sweets.getId());//受け取ったデータを引数で渡してリポジトリからDB書き換え
			}
		}
		return shoppingList;//updateBuyの実行でshoppingListの値を返す
	}

}
