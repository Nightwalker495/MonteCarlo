#include "UniformDiscrRndGen.h"

using namespace app;
using namespace std;

static unsigned genSeed();

UniformDiscrRndGen::UniformDiscrRndGen(int min, int max)
{
	gen_ = new mt19937(genSeed());
	dist_ = new uniform_int_distribution<>(min, max);
}

UniformDiscrRndGen::UniformDiscrRndGen(const UniformDiscrRndGen& other)
{
	gen_ = new mt19937(genSeed());
	dist_ = new uniform_int_distribution<>(other.dist_->a(), other.dist_->b());
}

UniformDiscrRndGen::~UniformDiscrRndGen()
{
	delete gen_;
	delete dist_;
}

UniformDiscrRndGen& UniformDiscrRndGen::operator=(
	const UniformDiscrRndGen& other)
{
	if (this != &other)
	{
		delete gen_;
		gen_ = new mt19937(genSeed());

		delete dist_;
		dist_ = new uniform_int_distribution<>(other.dist_->a(),
			other.dist_->b());
	}

	return *this;
}

int UniformDiscrRndGen::sample() const
{
	return (*dist_)(*gen_);
}

static unsigned genSeed()
{
	random_device rd;

	return rd();
}