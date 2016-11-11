class CreateUsers < ActiveRecord::Migration[5.0]
  def change
    create_table :users do |t|
      t.string :name
      t.date :birthday
      t.string :sex
      t.string :address
      t.string :avatar
      t.boolean :admin, default: false

      t.timestamps
    end
  end
end
