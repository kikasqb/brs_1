class CreateBooks < ActiveRecord::Migration[5.0]
  def change
    create_table :books do |t|
      t.references :category, foreign_key: true
      t.string :title
      t.datetime :publish_date
      t.string :author
      t.integer :number_of_pages
      t.text :introduce
      t.string :cover
      t.boolean :bought, default: true
      t.float :rate, default: Settings.default_rate
      t.boolean :deleted, default: false

      t.timestamps
    end
  end
end
