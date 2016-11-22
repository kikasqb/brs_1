namespace :admin do
  desc I18n.t :create_admin
  task create_admin: :environment do
    admin = User.new
    print I18n.t "rake.create_admin.input",
      attributes: I18n.t("rake.create_admin.name")
    admin.name = STDIN.gets.chomp
    print I18n.t "rake.create_admin.input",
      attributes: I18n.t("rake.create_admin.email")
    admin.email = STDIN.gets.chomp
    print I18n.t "rake.create_admin.input",
      attributes: I18n.t("rake.create_admin.address")
    admin.address = STDIN.gets.chomp
    print I18n.t "rake.create_admin.input",
      attributes: I18n.t("rake.create_admin.password")
    admin.password = STDIN.gets.chomp
    print I18n.t "rake.create_admin.input",
      attributes: I18n.t("rake.create_admin.confirmation")
    admin.password_confirmation = STDIN.gets.chomp
    if admin.save
       puts I18n.t :saved
    else
      print "\n\t"
      puts I18n.t :error
      admin.errors.full_messages.each do |message|
        print "\t\t"
        puts message
      end
    end
  end
  task remove_processed_request: :environment do
    if Request.have_processed.destroy_all
      puts I18n.t :destroy_request
    else
      puts I18n.t :error
    end
  end
end