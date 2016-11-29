User.create!(
  name: "Tien",
  email: "tripletee.3t@gmail.com",
  address: "Earth",
  birthday: "1995/03/13",
  sex: "Male",
  password: "12312311",
  password_confirmation: "12312311",
  admin: true,
)
User.create!(
  name: "Quang Tien",
  email: "phanqtien@gmail.com",
  address: "Earth",
  birthday: "1995/03/13",
  sex: "Male",
  password: "12312311",
  password_confirmation: "12312311",
)
50.times do |n|
  name = Faker::Name.name
  email = "example-#{n+1}@framgia.org"
  password = "123123"
  User.create!(
    name: name,
    email: email,
    address: "Galaxy",
    birthday: "1995/03/03",
    sex: "Male",
    password: password,
    password_confirmation: password,
  )
end
10.times do |n|
  name = Faker::Pokemon.name
  Category.create! name: name
end
categorires = Category.all
categorires.each do |category|
  puts "Category: #{category.id}"
  rand(10..(50 - category.id + 10)).times do |n|
    book = category.books.create title: Faker::Book.title,
      publish_date: Time.now, author: Faker::Book.author,
      number_of_pages:Faker::Number.number(3),
      introduce: Faker::Lorem.paragraph(2), rate: 0
    id_user_first = User.first.id
    id_user_last = User.last.id
    rand(4..10).times do |i|
      review = book.reviews.create user_id: rand(id_user_first..id_user_last),
        title: Faker::Lorem.sentence(rand(1..3)),
        content: Faker::Lorem.paragraph(5, true, 5),
        rate: rand(1..5)
        rand(3..8).times do |j|
          cmt = review.commentators.create user_id: rand(id_user_first..id_user_last)
          cmt.comments.create content: Faker::Lorem.sentence(3)
          puts "#{book.id} #{review.id} #{cmt.id}"
        end
    end
  end
end
10.times do |n|
  book = Book.new title: Faker::Book.title,
    publish_date: Time.now, author: Faker::Book.author,
    number_of_pages: Faker::Number.number(3),
    introduce: Faker::Lorem.paragraph(10, true, 4), rate: 0, bought: false,
    category_id: Category.first.id
  if book.save
    Request.create book_id: book.id,user_id: rand(User.first.id..User.last.id),
      description: Faker::Lorem.paragraph(5, true, 3)
  end
end
